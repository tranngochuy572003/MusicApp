package com.example.musicapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class dnmh8_loginwithemailorphoneActivity extends AppCompatActivity {
    private LinearLayout tabEmailContent, tabPhoneContent;
    private Button tabEmail, tabPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnmh8_loginwithemailorphone);

        tabEmailContent = findViewById(R.id.tabEmailContent);
        tabPhoneContent = findViewById(R.id.tabPhoneContent);
        tabEmail = findViewById(R.id.tabEmail);
        tabPhone = findViewById(R.id.tabPhone);

        tabEmail.setOnClickListener(v -> {
            // Hiển thị nội dung Email, ẩn nội dung Phone
            tabEmailContent.setVisibility(View.VISIBLE);
            tabPhoneContent.setVisibility(View.GONE);

            // Cập nhật màu nền cho các tab để dễ nhận biết
            tabEmail.setBackgroundColor(getResources().getColor(R.color.purple));
            tabPhone.setBackgroundColor(getResources().getColor(R.color.gray_701));
        });

        tabPhone.setOnClickListener(v -> {
            // Hiển thị nội dung Phone, ẩn nội dung Email
            tabPhoneContent.setVisibility(View.VISIBLE);
            tabEmailContent.setVisibility(View.GONE);

            // Cập nhật màu nền cho các tab
            tabPhone.setBackgroundColor(getResources().getColor(R.color.purple));
            tabEmail.setBackgroundColor(getResources().getColor(R.color.gray_701));
        });

        // Mặc định hiển thị tab Email khi ứng dụng mở
        tabEmailContent.setVisibility(View.VISIBLE);
        tabPhoneContent.setVisibility(View.GONE);
    }

}

