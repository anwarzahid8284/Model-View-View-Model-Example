package com.example.mvvmdesingpatern.Adapter;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdesingpatern.MVVM.UserRepository;
import com.example.mvvmdesingpatern.R;
import com.example.mvvmdesingpatern.RoomDB.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVHolder>{
    List<User> userList;
    Context context;
    LayoutInflater layoutInflater;
    Application application;
    public UserAdapter(List<User> userList, Context context, Application application) {
        this.userList=userList;
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.user_item, parent, false);
        return new UserVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVHolder holder, int position) {
        holder.textViewUserName.setText(userList.get(position).getUserName());
        holder.textViewUserAge.setText(String.valueOf(userList.get(position).getUserAge()));//correct this line

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserVHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName,textViewUserAge;
        ImageView imageView;
        public UserVHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName=itemView.findViewById(R.id.userItem_UserID);
            textViewUserAge=itemView.findViewById(R.id.userItem_UserAgeID);
            imageView=itemView.findViewById(R.id.userItem_DropDownImageID);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu=new PopupMenu(context, imageView);
                    popupMenu.getMenuInflater().inflate(R.menu.drop_down_menu,popupMenu.getMenu());
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.updateItemID:
                                    updateRecord(getAdapterPosition());
                                    break;
                                case R.id.deleteItemID:
                                    deleteRecord(getAdapterPosition());
                                    break;
                            }
                            return false;
                        }
                    });

                }


            });
        }
    }
    private void updateRecord(int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        LayoutInflater inflater=this.layoutInflater;
        View view=inflater.inflate(R.layout.update_item,null);
        alert.setView(view);
        EditText editTextUpdateName=(EditText) view.findViewById(R.id.updateNameID);
        EditText editTextUpdatedAge=(EditText) view.findViewById(R.id.updateAgeID);
        Button buttonUpdate=(Button) view.findViewById(R.id.updateBtnID);


        AlertDialog alertDialog=alert.create();
        alertDialog.show();
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateName=editTextUpdateName.getText().toString().trim();
                String updateAge=editTextUpdatedAge.getText().toString().trim();
                UserRepository userRepository=new UserRepository(application);
                int userId=userList.get(position).getUserID();
                try {
                    userRepository.updateUser(userId,updateName,Integer.parseInt(updateAge));

                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                alertDialog.dismiss();
            }
        });

    }

    private void deleteRecord(int position) {
        UserRepository userRepository=new UserRepository(application);
        userRepository.deleteUser(userList.get(position));

    }
}

























