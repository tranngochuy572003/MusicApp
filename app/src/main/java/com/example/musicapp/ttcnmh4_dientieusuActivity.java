package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ttcnmh4_dientieusuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttcnmh4_dientieusu);

        // Xử lý nút "Cập nhật màn 6"
        Button capNhatMan6 = findViewById(R.id.capnhatman3);
        capNhatMan6.setOnClickListener(view -> {
            // Logic xử lý cập nhật tại đây (nếu có)
        });

        // Xử lý ImageView "quaytrai11" để quay lại màn hình ttcnmh2
        ImageView quayTrai11 = findViewById(R.id.quaytrai14);
        quayTrai11.setOnClickListener(view -> {
            // Quay lại màn hình MainActivity (ttcnmh2)
            Intent intent = new Intent(ttcnmh4_dientieusuActivity.this, ttcnmh2_thongtincanhanActivity.class);
            startActivity(intent);
            finish(); // Đóng Activity hiện tại để không quay lại được bằng nút back
        });
    }
}
