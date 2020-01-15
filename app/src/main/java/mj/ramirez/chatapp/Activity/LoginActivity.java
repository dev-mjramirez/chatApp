package mj.ramirez.chatapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mj.ramirez.chatapp.R;
import mj.ramirez.chatapp.Utils.DialogUtils;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Dialog progressDialog;
    private DialogUtils dialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        dialogUtils = new DialogUtils();
        progressDialog = dialogUtils.showProgress(this);

        customActionBar();

        etUsername.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);

    }

    private void customActionBar(){
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_custom_header);
        getSupportActionBar().setElevation(1);
    }

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword) EditText etPassword;
    private boolean checkFieldInformation(){
        boolean empty = false;
        if (etUsername.getText().toString().equalsIgnoreCase("")){
            empty=true;
        }
        if (etPassword.getText().toString().equalsIgnoreCase("")){
            empty=true;
        }
        if (etUsername.length() < 8 || etUsername.length() > 16){
            empty=true;
        }
        if (etPassword.length() < 8 || etPassword.length() > 16){
            empty=true;
        }
        showHiderError(empty);
        return empty;
    }


    @BindView(R.id.tvUsernameError) TextView tvUsernameError;
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

    @OnClick(R.id.btnLogin)
    void login(){
        if (checkFieldInformation()) return;
        String email = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    progressDialog.dismiss();
                    if (task.isSuccessful()){
                        startActivity(new Intent(this,ChatActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        overridePendingTransition(R.anim.exit, R.anim.enter);
                    } else {
                        showHiderError(true);
                    }
                });
    }

    @OnClick(R.id.tvSignUp)
    void signup(){
        startActivity(new Intent(this,SignUpActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        overridePendingTransition(R.anim.exit, R.anim.enter);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            showHiderError(false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
