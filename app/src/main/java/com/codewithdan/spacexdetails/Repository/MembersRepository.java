package com.codewithdan.spacexdetails.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.codewithdan.spacexdetails.Dao.MemberDao;
import com.codewithdan.spacexdetails.Database.MembersDatabase;
import com.codewithdan.spacexdetails.model.Members;

import java.util.List;

public class MembersRepository {

    private MembersDatabase database;
    private LiveData<List<Members>> getAllMembers;
    

    public MembersRepository(Application application) {

        database = MembersDatabase.getInstance(application);
        getAllMembers = database.memberDao().getAllMembers();

    }

    public void insert(List<Members> membersList) {

        new InsertAsynTask(database).execute(membersList);
    }

    public LiveData<List<Members>> getGetAllMembers() {
        return getAllMembers;
    }

    static class InsertAsynTask extends AsyncTask<List<Members>, Void, Void> {
        private MemberDao memberDao;

        InsertAsynTask(MembersDatabase membersDatabase) {
            memberDao = membersDatabase.memberDao();
        }

        @Override
        protected Void doInBackground(List<Members>... lists) {
            memberDao.insert(lists[0]);
            return null;
        }
    }




}
