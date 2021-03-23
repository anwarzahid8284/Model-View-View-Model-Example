package com.example.mvvmdesingpatern.MVVM;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mvvmdesingpatern.RoomDB.User;
import com.example.mvvmdesingpatern.RoomDB.UserDB;

import java.util.List;

public  class UserRepository {
    private UserDB userDB;
    public LiveData<List<User>> getAllUser;
    public UserRepository(Application application){
        userDB=UserDB.getINSTANCE(application);
        getAllUser=userDB.userDao().getAllUser();
    }
    public LiveData<List<User>> getGetAllUser(){
        return getAllUser;
    }
    public void insertUser(User user){
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDB.userDao().insertUser(user);
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public void deleteUser(User user){
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDB.userDao().deleteUser(user);
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public void updateUser(int userID,String updateName,int updatedAge){
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDB.userDao().updateUser(userID,updateName,updatedAge);
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
