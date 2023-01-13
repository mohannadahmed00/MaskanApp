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

public class ForgottenPassActivity extends AppCompatActivity {

    @BindView(R.id.et_email_forgotten)
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_pass);
        ButterKnife.bind(this);
        changeStatBarColor(ForgottenPassActivity.this, R.color.colorBase);
    }

    void changeStatBarColor(AppCompatActivity activity, int color) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(activity, color));
    }

    @OnClick({R.id.btn_reset_forgotten, R.id.tv_sign_in_forgotten})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reset_forgotten:
                Toast.makeText(this, "reset", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_sign_in_forgotten:
                startActivity(new Intent(this,SignInActivity.class));
                break;
        }
    }
}