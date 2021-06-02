package com.codewithdan.spacexdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codewithdan.spacexdetails.Adapter.MembersAdapter;
import com.codewithdan.spacexdetails.Dao.MemberDao;
import com.codewithdan.spacexdetails.Network.Api;
import com.codewithdan.spacexdetails.Repository.MembersRepository;
import com.codewithdan.spacexdetails.ViewModel.MembersViewModel;
import com.codewithdan.spacexdetails.model.Members;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://api.spacexdata.com/v4/";

    private MembersViewModel membersViewModel;
    private RecyclerView recyclerView;
    private MembersAdapter membersAdapter;
    private List<Members> membersList;
    private MembersRepository membersRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        membersList = new ArrayList<>();
        membersAdapter = new MembersAdapter(this,membersList);
        membersRepository = new MembersRepository(getApplication());
        membersViewModel = new ViewModelProvider(this).get(MembersViewModel.class);


        membersViewModel.getAllMembers().observe(this, new Observer<List<Members>>() {
            @Override
            public void onChanged(List<Members> membersList) {
                membersAdapter.getAllMembers(membersList);
                recyclerView.setAdapter(membersAdapter);
            }
        });

        networkRequest();

    }

    private void networkRequest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_DATA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Members>> call = api.getAllMembers();
        call.enqueue(new Callback<List<Members>>() {
            @Override
            public void onResponse(Call<List<Members>> call, Response<List<Members>> response) {
                if(response.isSuccessful()){
                    membersRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Members>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong !!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}