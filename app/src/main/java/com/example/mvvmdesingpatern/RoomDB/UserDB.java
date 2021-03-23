package com.example.mvvmdesingpatern.RoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities ={User.class},version = 1,exportSchema = false)
public abstract class UserDB extends RoomDatabase {
    public static final String DB_NAME="UserDB";
    private static volatile UserDB INSTANCE;
    public abstract UserDao userDao();
    public static UserDB getINSTANCE(Context context){
        if(INSTANCE==null){
            synchronized (UserDB.class){
                INSTANCE= Room.databaseBuilder(context,UserDB.class,DB_NAME).build();
            }
        }
        return INSTANCE;
    }

}
