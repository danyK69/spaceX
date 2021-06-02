package com.codewithdan.spacexdetails.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.codewithdan.spacexdetails.Repository.MembersRepository;
import com.codewithdan.spacexdetails.model.Members;

import java.util.List;

public class MembersViewModel extends AndroidViewModel {

    private MembersRepository membersRepository;
    private LiveData<List<Members>> getAllMembers;

    public MembersViewModel(@NonNull  Application application) {
        super(application);

        membersRepository = new MembersRepository(application);
        getAllMembers = membersRepository.getGetAllMembers();

    }

    public void insert(List<Members> list){
        membersRepository.insert(list);
    }

    public LiveData<List<Members>> getAllMembers(){
        return getAllMembers;
    }


}
