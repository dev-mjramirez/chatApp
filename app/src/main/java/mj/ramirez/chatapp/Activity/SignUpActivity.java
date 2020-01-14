package mj.ramirez.chatapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mj.ramirez.chatapp.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        customActionBar();

    }

    private void customActionBar(){
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_custom_header);
        getSupportActionBar().setElevation(1);
    }

    @BindView(R.id.tvUsernameError)
    TextView tvUsernameError;
    @BindView(R.id.tvPasswordError) TextView tvPasswordError;
    private void showHiderError(boolean show){
        if (show){
            tvUsernameError.setVisibility(View.VISIBLE);
            tvPasswordError.setVisibility(View.VISIBLE);
        } else {
            tvUsernameError.setVisibility(View.GONE);
            tvPasswordError.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.tvLogin)
    void login(){
        startActivity(new Intent(this,LoginActivity.class));
    }
}
