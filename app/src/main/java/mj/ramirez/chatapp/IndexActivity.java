package mj.ramirez.chatapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignup)
    void login(){
        startActivity(new Intent(this,LoginActivity.class));
    }

    @OnClick(R.id.btnLogin)
    void signup(){
        startActivity(new Intent(this,SignUpActivity.class));
    }
}
