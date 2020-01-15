package mj.ramirez.chatapp.CustomView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import mj.ramirez.chatapp.Activity.LoginActivity;
import mj.ramirez.chatapp.R;

public class ChatCustomHeaderTitle extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_chat_custom_header);
        getSupportActionBar().setElevation(1);

        tvTittle = findViewById(R.id.tvTittle);

    }

    public void setActionBarTitle(String title, int textColor) {
        tvTittle.setText(title);
        if (textColor != 0) {
            tvTittle.setTextColor(textColor);
        } else {
            tvTittle.setTextColor(getResources().getColor(android.R.color.black));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            overridePendingTransition(R.anim.reverseexit, R.anim.reverseenter);
        }
    }
}

