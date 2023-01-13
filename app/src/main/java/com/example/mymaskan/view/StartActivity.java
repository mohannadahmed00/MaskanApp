package com.example.mymaskan.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mymaskan.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);


        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    void changeStatBarColor(AppCompatActivity activity, int color) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(activity, color));
    }


    @OnClick({R.id.btn_sign_up_start, R.id.btn_google_start, R.id.btn_facebook_start, R.id.tv_sign_in_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_start:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.btn_google_start:
                Toast.makeText(this, "google+", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_facebook_start:
                startActivity(new Intent(this, MainActivity.class));

                break;
            case R.id.tv_sign_in_start:

                startActivity(new Intent(this, SignInActivity.class));
                break;
        }
    }
}