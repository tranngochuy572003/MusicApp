package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dnmh6_SettingActivity extends AppCompatActivity {
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnmh6_setting_view);
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(dnmh6_SettingActivity.this, "Đăng xuất thành công", Toast.LENGTH_LONG).show();

            Intent launchActivity = new Intent(dnmh6_SettingActivity.this, dnmh1_LoginActivity.class);
            startActivity(launchActivity);
        });
    }
}
