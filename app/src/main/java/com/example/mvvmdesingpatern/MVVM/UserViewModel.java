package com.example.mvvmdesingpatern.MVVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmdesingpatern.RoomDB.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    public UserRepository userRepository;
    LiveData<List<User>> getAllUser;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        getAllUser = userRepository.getGetAllUser();
    }

    public LiveData<List<User>> getGetAllUser(){
        return getAllUser;
    }
}
