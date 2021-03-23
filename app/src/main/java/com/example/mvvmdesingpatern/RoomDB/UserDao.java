package com.example.mvvmdesingpatern.RoomDB;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("select * from user")
    LiveData<List<User>> getAllUser();

    @Delete
    void deleteUser(User user);

    @Query("Update user set name = :updatedUserName, age = :updatedUserAge where userID = :userId")
    void updateUser(int userId,String updatedUserName,int updatedUserAge);

}
