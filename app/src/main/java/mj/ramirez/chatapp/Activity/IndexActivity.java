package mj.ramirez.chatapp.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mj.ramirez.chatapp.R;

public class IndexActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart(){
        super.onStart();
        if (mAuth.getCurrentUser() != null) startActivity(new Intent(this,ChatActivity.class));
    }

    @OnClick(R.id.btnSignup)
    void signup(){
        startActivity(new Intent(this,SignUpActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        overridePendingTransition(R.anim.exit, R.anim.enter);
    }

    @OnClick(R.id.btnLogin)
    void login(){
        startActivity(new Intent(this,LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        overridePendingTransition(R.anim.exit, R.anim.enter);
    }

}
