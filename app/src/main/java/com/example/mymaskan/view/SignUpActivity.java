package com.example.mymaskan.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mymaskan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.et_first_name_sign_up)
    EditText etFirstName;
    @BindView(R.id.et_last_name_sign_up)
    EditText etLastName;
    @BindView(R.id.et_phone_sign_up)
    EditText etPhone;
    @BindView(R.id.et_email_sign_up)
    EditText etEmail;
    @BindView(R.id.et_password_sign_up)
    EditText etPassword;
    @BindView(R.id.et_retype_password_sign_up)
    EditText etRetypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        changeStatBarColor(SignUpActivity.this, R.color.colorBase);
    }

    void changeStatBarColor(AppCompatActivity activity, int color) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(activity, color));
    }

    @OnClick({R.id.tv_show_password_sign_up, R.id.tv_show_retype_password_sign_up, R.id.btn_sign_up_sign_up, R.id.tv_sign_in_sign_up})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_show_password_sign_up:
                Toast.makeText(this, "show or hide", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_show_retype_password_sign_up:
                Toast.makeText(this, "hide or show", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_sign_up_sign_up:
                Toast.makeText(this, "sign up", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_sign_in_sign_up:
                startActivity(new Intent(this, SignInActivity.class));
                break;
        }
    }
}