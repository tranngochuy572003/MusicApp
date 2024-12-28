package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class ttcnmh5_chongioitinhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttcnmh5_chongioitinh);
        String gender = getIntent().getStringExtra("gioitinh");

        RadioButton radioMale = findViewById(R.id.editTextText3);
        RadioButton radioFemale = findViewById(R.id.dientenman5);
        RadioButton radioOther = findViewById(R.id.editTextText2);

        switch (gender) {
            case "Nam":
                radioMale.setChecked(true); // Đánh dấu RadioButton Nam là được chọn
                break;
            case "Nữ":
                radioFemale.setChecked(true); // Đánh dấu RadioButton Nữ là được chọn
                break;
            case "Khác":
                radioOther.setChecked(true); // Đánh dấu RadioButton Khác là được chọn
                break;
            default:
                // Nếu không có giá trị hợp lệ, không chọn gì cả
                break;
        }

        // Xử lý nút "Cập nhật màn 6"
        Button capNhatMan6 = findViewById(R.id.capnhatman7);
        capNhatMan6.setOnClickListener(view -> {
            // Logic xử lý cập nhật tại đây (nếu có)
        });

        // Xử lý ImageView "quaytrai11" để quay lại màn hình ttcnmh2
        ImageView quayTrai11 = findViewById(R.id.quaytrai15);
        quayTrai11.setOnClickListener(view -> {
            // Quay lại màn hình MainActivity (ttcnmh2)
            Intent intent = new Intent(ttcnmh5_chongioitinhActivity.this, ttcnmh2_thongtincanhanActivity.class);
            startActivity(intent);
            finish(); // Đóng Activity hiện tại để không quay lại được bằng nút back
        });
    }
}
