package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ttcnmh3_dientenActivity extends AppCompatActivity {
EditText dientenman4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttcnmh3_dienten);

        String name = getIntent().getStringExtra("ten");
        dientenman4 = findViewById(R.id.dientenman4);
        dientenman4.setText(name);
        // Xử lý nút "Cập nhật màn 6"
        Button capNhatMan6 = findViewById(R.id.capnhatman6);
        capNhatMan6.setOnClickListener(view -> {
            // Logic xử lý cập nhật tại đây (nếu có)
        });

        // Xử lý ImageView "quaytrai11" để quay lại màn hình ttcnmh2
        ImageView quayTrai11 = findViewById(R.id.quaytrai11);
        quayTrai11.setOnClickListener(view -> {

            Intent intent = new Intent(ttcnmh3_dientenActivity.this, ttcnmh2_thongtincanhanActivity.class);
            startActivity(intent);
            finish(); // Đóng Activity hiện tại để không quay lại được bằng nút back
        });
    }
}
