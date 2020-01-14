package mj.ramirez.chatapp.Activity;

import android.os.Bundle;

import mj.ramirez.chatapp.CustomView.ChatCustomHeaderTitle;
import mj.ramirez.chatapp.R;

public class ChatActivity extends ChatCustomHeaderTitle {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

    }
}
