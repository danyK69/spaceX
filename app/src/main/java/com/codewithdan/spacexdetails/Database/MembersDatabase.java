package com.codewithdan.spacexdetails.Database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.codewithdan.spacexdetails.Dao.MemberDao;
import com.codewithdan.spacexdetails.model.Members;

@Database(entities = {Members.class} , version = 2)
public abstract class MembersDatabase extends RoomDatabase {

    private  static final String DATABASE_NAME = "MembersDatabase";

    public abstract MemberDao memberDao();

    public static volatile  MembersDatabase INSTANCE;

    public static MembersDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (MembersDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, MembersDatabase.class,DATABASE_NAME).addCallback(callback)
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };

    static class PopulateAsynTask extends AsyncTask<Void,Void,Void>{

        private MemberDao memberDao;
        PopulateAsynTask(MembersDatabase membersDatabase)
        {
            memberDao = membersDatabase.memberDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }

}
