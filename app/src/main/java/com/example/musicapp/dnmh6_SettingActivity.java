package com.example.musicapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class dnmh6_SettingActivity extends AppCompatActivity {
    Button btnLogout;
    ImageView ivArrowThongTinCaNhan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnmh6_setting_view);
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            logout();
            Toast.makeText(dnmh6_SettingActivity.this, "Đăng xuất thành công", Toast.LENGTH_LONG).show();
        });

        // Ánh xạ ImageView ivArrowThongTinCaNhan
        ivArrowThongTinCaNhan = findViewById(R.id.ivArrowThongTinCaNhan);

        // Đặt sự kiện cho ivArrowThongTinCaNhan
        ivArrowThongTinCaNhan.setOnClickListener(v -> {
            // Chuyển sang màn hình ttcnmh2_thongtincanhanActivity
            Intent intent = new Intent(dnmh6_SettingActivity.this, ttcnmh2_thongtincanhanActivity.class);
            startActivity(intent);
        });

    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();

        // Chuyển hướng người dùng về màn hình đăng nhập
        Intent intent = new Intent(dnmh6_SettingActivity.this, dnmh1_LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa ngăn xếp
        startActivity(intent);
    }
}
