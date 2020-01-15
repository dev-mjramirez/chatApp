package mj.ramirez.chatapp.Activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mj.ramirez.chatapp.Adapter.ChatAdapter;
import mj.ramirez.chatapp.CustomView.ChatCustomHeaderTitle;
import mj.ramirez.chatapp.CustomView.RecyclerViewEmptySupport;
import mj.ramirez.chatapp.Model.ChatMessage;
import mj.ramirez.chatapp.R;

public class ChatActivity extends ChatCustomHeaderTitle {

    private RecyclerViewEmptySupport rvChat;
    private RelativeLayout emptyResult;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> results = new ArrayList<>();
    private List<ChatMessage> sortByDesc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        rvChat = (RecyclerViewEmptySupport) findViewById(R.id.rv);
        emptyResult = findViewById(R.id.noResult);

        setRecyclerNotificationAdapter();
        displayChatMessage();
    }

    private void setRecyclerNotificationAdapter() {
        int scrollPosition = 0;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);

        // If a layout manager has already been set, get current scroll position.
        if (rvChat.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) rvChat.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        rvChat.setLayoutManager(linearLayoutManager);
        rvChat.scrollToPosition(scrollPosition);
        rvChat.setHasFixedSize(true);

        chatAdapter = new ChatAdapter();
        rvChat.setAdapter(chatAdapter);
        rvChat.setEmptyView(emptyResult);
    }

    @BindView(R.id.etMessage) EditText etMessage;
    @OnClick(R.id.btnSend)
    void send(){
        FirebaseDatabase.getInstance().getReference().push()
                .setValue(new ChatMessage(etMessage.getText().toString(),
                        Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail()))
                .addOnCompleteListener(task -> {
                    if (task.isComplete()) {
                        etMessage.setText("");
                        displayChatMessage();
                    }
                });
    }

    private void displayChatMessage(){
        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                results.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setMessageText(Objects.requireNonNull(data.getValue(ChatMessage.class)).getMessageText());
                    chatMessage.setMessageTime(Objects.requireNonNull(data.getValue(ChatMessage.class)).getMessageTime());
                    chatMessage.setMessageUser(Objects.requireNonNull(data.getValue(ChatMessage.class)).getMessageUser());
                    results.add(chatMessage);
                }
                sortByDesc.clear();
                for (int i=(results.size() - 1); i > 0; i--){
                    ChatMessage chatMessage = results.get(i);
                    sortByDesc.add(chatMessage);
                }

                chatAdapter.setList(new ArrayList<>(sortByDesc));
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
