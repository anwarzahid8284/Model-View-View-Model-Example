package com.example.mvvmdesingpatern.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdesingpatern.Adapter.UserAdapter;
import com.example.mvvmdesingpatern.MVVM.UserRepository;
import com.example.mvvmdesingpatern.MVVM.UserViewModel;
import com.example.mvvmdesingpatern.R;
import com.example.mvvmdesingpatern.RoomDB.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    UserViewModel userViewModel;
    UserRepository userRepository;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<User> userList;
    EditText editTextUserName, editTextUserAge;
    Button buttonAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.activityMain_RecyclerViewID);
        editTextUserName = findViewById(R.id.activityMain_EditTextUserNameID);
        editTextUserAge = findViewById(R.id.activityMain_EditTextAgeID);
        buttonAddUser = findViewById(R.id.activityMain_BtnAddUserID);
        userRepository=new UserRepository(getApplication());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList, MainActivity.this,getApplication());
        recyclerView.setAdapter(userAdapter);

        userViewModel=new ViewModelProvider(this,getDefaultViewModelProviderFactory()).get(UserViewModel.class);
        userViewModel.getGetAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> getList) {
                userList.clear();
                userList.addAll(getList);
                userAdapter.notifyDataSetChanged();
            }
        });
        buttonAddUser.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        String userName=editTextUserName.getText().toString().trim();
        String age= editTextUserAge.getText().toString().trim();
        int userAge=Integer.parseInt(age);

        // first make object and set the value and pass that object to insert method
        User user=new User();
        user.setUserName(userName);
        user.setUserAge(userAge);
        userRepository.insertUser(user);
    }
}