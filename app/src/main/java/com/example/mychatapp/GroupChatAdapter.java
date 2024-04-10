package com.example.mychatapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GroupChatAdapter extends RecyclerView.Adapter<GroupChatAdapter.MyViewHolder> {
    private Context context;
    private List<GroupMessageModel> groupMessageModels;

    public GroupChatAdapter(Context context) {
        this.context = context;
        this.groupMessageModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_row,parent ,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GroupMessageModel messageModel = groupMessageModels.get(position);
        holder.groupname.setText(messageModel.getGroupName());
        holder.groupcreator.setText(messageModel.getGroupCreator());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("groupids ",messageModel.getGroupIds());
                Log.d("uid",FirebaseAuth.getInstance().getCurrentUser().getUid());
                if(messageModel.getGroupIds().contains(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    Intent intent = new Intent(context, GroupChatActivity.class);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context,"You don't have permission to access this group.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void add(GroupMessageModel groupMessageModel){
        groupMessageModels.add(groupMessageModel);
        notifyDataSetChanged();
    }
    public void clear(){
        groupMessageModels.clear();
        notifyDataSetChanged();
    }

    private String formatTime(String timestamp) {
        long timeInMillis = Long.parseLong(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(new Date(timeInMillis));
    }

    @Override
    public int getItemCount() {
        return groupMessageModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView message, sender, time;
        TextView groupname, groupcreator;

        public MyViewHolder(View itemView) {
            super(itemView);
            groupname = itemView.findViewById(R.id.groupname);
            groupcreator = itemView.findViewById(R.id.groupcreator);
        }
    }
}
