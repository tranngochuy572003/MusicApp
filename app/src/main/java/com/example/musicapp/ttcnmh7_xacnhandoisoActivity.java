package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ttcnmh7_xacnhandoisoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttcnmh7_xacnhandoiso);

        // Xử lý nút "Cập nhật màn 6"
        Button capNhatMan6 = findViewById(R.id.button);
        capNhatMan6.setOnClickListener(view -> {
            // Logic xử lý cập nhật tại đây (nếu có)
        });

        // Xử lý ImageView "quaytrai11" để quay lại màn hình ttcnmh2
        ImageView quayTrai17 = findViewById(R.id.quaytrai17);
        quayTrai17.setOnClickListener(view -> {
            // Quay lại màn hình MainActivity (ttcnmh2)
            Intent intent = new Intent(ttcnmh7_xacnhandoisoActivity.this, ttcnmh2_thongtincanhanActivity.class);
            startActivity(intent);
            finish(); // Đóng Activity hiện tại để không quay lại được bằng nút back
        });
    }
}
