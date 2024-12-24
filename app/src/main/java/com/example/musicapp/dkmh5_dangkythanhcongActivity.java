package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class dkmh5_dangkythanhcongActivity extends AppCompatActivity {

    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dkmh5_dangkythanhcong); // Liên kết với layout đăng ký thành công
        // Ánh xạ Button "ĐI ĐẾN ĐĂNG NHẬP"
        btnBack = findViewById(R.id.btnBack);

        // Xử lý sự kiện click
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dkmh5_dangkythanhcongActivity.this, dnmh8_loginwithemailorphoneActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
