package com.example.mymaskan.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mymaskan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {


    @BindView(R.id.et_email_sign_in)
    EditText etEmail;
    @BindView(R.id.et_password_sign_in)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        changeStatBarColor(SignInActivity.this, R.color.colorBase);

    }

    void changeStatBarColor(AppCompatActivity activity, int color) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(activity, color));
    }


    @OnClick({R.id.tv_show_password_sign_in, R.id.btn_sign_in_sign_in, R.id.tv_sign_up_sign_in, R.id.tv_forgotten_sign_in})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_show_password_sign_in:
                Toast.makeText(this, "show or hide", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_sign_in_sign_in:
                Toast.makeText(this, "sign in", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_sign_up_sign_in:
                startActivity(new Intent(this,SignUpActivity.class));
                break;
            case R.id.tv_forgotten_sign_in:
                startActivity(new Intent(this,ForgottenPassActivity.class));
                break;
        }
    }
}