package com.example.mychatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.List;

public class GroupChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText messageEditText;
    private ImageView sendButton;
    private DatabaseReference databaseReference;
    private GroupMessageAdapter groupMessageAdapter;
    private String groupName, groupIds, groupUserNames, groupCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        recyclerView = findViewById(R.id.chatrecycler_group);
        messageEditText = findViewById(R.id.messageEdit_group);
        sendButton = findViewById(R.id.sendMessageIcon_group);

        groupMessageAdapter = new GroupMessageAdapter(this);

        recyclerView.setAdapter(groupMessageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        groupName = getIntent().getStringExtra("groupName");
        groupIds = getIntent().getStringExtra("groupIds");
        groupUserNames = getIntent().getStringExtra("groupUserNames");
        groupCreator = getIntent().getStringExtra("groupCreator");


        databaseReference = FirebaseDatabase.getInstance().getReference("group_messages");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        // Add ValueEventListener for updating messages
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                groupMessageAdapter.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    GroupMessageModel messageModel = dataSnapshot.getValue(GroupMessageModel.class);
                    if (messageModel != null) {
                        groupMessageAdapter.add(messageModel);
                    }
                }
                groupMessageAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(groupMessageAdapter.getItemCount() - 1); // Scroll to bottom
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void sendMessage() {
        String messageText = messageEditText.getText().toString().trim();
        if (!messageText.isEmpty()) {
            String groupId = databaseReference.push().getKey();
            if (groupId != null) {
                String senderId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                long currentTime = System.currentTimeMillis();
                GroupMessageModel messageModel = new GroupMessageModel(groupId, senderId, messageText, String.valueOf(currentTime), groupName,groupUserNames,groupIds, groupCreator);
                databaseReference.child(groupId).setValue(messageModel);
                messageEditText.setText(""); // Clear the input field after sending
            }
        }
    }
}
