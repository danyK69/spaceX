package com.codewithdan.spacexdetails.Dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codewithdan.spacexdetails.model.Members;

import java.util.List;

@Dao
public interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Members> membersList);

    @Query("SELECT * FROM members")
    LiveData<List<Members>> getAllMembers();



}
