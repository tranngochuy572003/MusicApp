package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ttcnmh6_doisodienthoaiActivity extends AppCompatActivity {
    TextView Sodt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttcnmh6_doisodienthoai);
        String phone = getIntent().getStringExtra("dienthoai");
        Sodt= findViewById(R.id.Sodt);
        Sodt.setText(phone);
        // Xử lý nút "Cập nhật màn 6"
        Button Button3 = findViewById(R.id.button3);
        Button3.setOnClickListener(view -> {
            Intent intent = new Intent(ttcnmh6_doisodienthoaiActivity.this, ttcnmh7_xacnhandoisoActivity.class);
            startActivity(intent);// Logic xử lý cập nhật tại đây (nếu có)
        });

        // Xử lý ImageView "quaytrai11" để quay lại màn hình ttcnmh2
        TextView textView9 = findViewById(R.id.textView9);
        textView9.setOnClickListener(view -> {
            // Quay lại màn hình MainActivity (ttcnmh2)
            Intent intent = new Intent(ttcnmh6_doisodienthoaiActivity.this, ttcnmh2_thongtincanhanActivity.class);
            startActivity(intent);
            finish(); // Đóng Activity hiện tại để không quay lại được bằng nút back
        });
    }
}
