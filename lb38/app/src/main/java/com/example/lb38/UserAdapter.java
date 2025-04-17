package com.example.lb38;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> userList;
    private Context context;

    public UserAdapter(Context context, ArrayList<User> arrayList){
        this.context = context;
        this.userList = arrayList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<User> newData){
        userList.clear();
        userList.addAll(newData);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public  UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textViewName.setText(user.getUserName());
        holder.textViewMessage.setText(user.getUserMessage());
        holder.imageViewAvatar.setImageResource(user.getUserImageRes());


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewAvatar;
        public TextView textViewName;
        public TextView textViewMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
        }
    }
}

