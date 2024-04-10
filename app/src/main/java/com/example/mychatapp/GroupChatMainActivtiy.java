package com.example.mychatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class GroupChatMainActivtiy extends AppCompatActivity {
    private GroupChatAdapter groupChatAdapter;
    private RecyclerView recyclerView;
    private TextView groupUsers;
    private String userNames = "";
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat_main_activtiy);

        Toolbar toolbar = findViewById(R.id.toolbar_group_main);
        setSupportActionBar(toolbar);
//        String groupName = getIntent().getStringExtra("groupName");
//        String [] users = getIntent().getStringExtra("groupUserNames").split(" ");
//        for(String user: users){
//            userNames += user;
//        }


        getSupportActionBar().setTitle("Groups");
        groupUsers = findViewById(R.id.groupUsersToolbar);
//        groupUsers.setText(userNames);

        groupChatAdapter = new GroupChatAdapter(this);
        recyclerView = findViewById(R.id.recycler_group_main);

        recyclerView.setAdapter(groupChatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference("group_messages");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                groupChatAdapter.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    GroupMessageModel groupMessageModel = dataSnapshot.getValue(GroupMessageModel.class);
                    //this condition is used to check if the userid going to be changed is same or not if not same then ->
                    if(groupMessageModel!=null && groupMessageModel.getGroupId()!=null && !groupMessageModel.getGroupId().equals(FirebaseAuth.getInstance().getUid())){
                        groupChatAdapter.add(groupMessageModel);
                    }
                }
                groupChatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //instantiate menu xml to menu objects
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(GroupChatMainActivtiy.this, SigninActivity.class));
            finish();
            return  true;
        }

        return false;
    }
}