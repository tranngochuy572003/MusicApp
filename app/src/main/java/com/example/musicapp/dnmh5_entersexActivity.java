package com.example.musicapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dnmh5_entersexActivity extends AppCompatActivity {
    private String selectedGender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnmh5_entersex_view);

        // Ánh xạ các View
        TextView maleButton = findViewById(R.id.optionMale);
        TextView femaleButton = findViewById(R.id.optionFemale);
        TextView otherButton = findViewById(R.id.optionOther);
        Button btnComplete = findViewById(R.id.btnComplete);
        TextView skipButton = findViewById(R.id.btnSkip);

        // Xử lý sự kiện chọn giới tính
        maleButton.setOnClickListener(v -> {
            selectedGender = "Nam";
            updateButtonState(maleButton, femaleButton, otherButton);
        });

        femaleButton.setOnClickListener(v -> {
            selectedGender = "Nữ";
            updateButtonState(femaleButton, maleButton, otherButton);
        });

        otherButton.setOnClickListener(v -> {
            selectedGender = "Khác";
            updateButtonState(otherButton, maleButton, femaleButton);
        });

        btnComplete.setOnClickListener(v -> {
            if (selectedGender.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn giới tính!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(dnmh5_entersexActivity.this, dkmh5_dangkythanhcongActivity.class);
                intent.putExtra("gender", selectedGender);
                startActivity(intent);
            }
        });

        skipButton.setOnClickListener(v -> {
            startActivity(new Intent(dnmh5_entersexActivity.this, dkmh5_dangkythanhcongActivity.class));
        });
    }

    // Cập nhật trạng thái nút (highlight nút được chọn)
    private void updateButtonState(TextView selected, TextView... others) {
        selected.setBackgroundColor(Color.parseColor("#4CAF50"));
        selected.setTextColor(Color.WHITE);
        for (TextView btn : others) {
            btn.setBackgroundColor(Color.parseColor("#E0E0E0"));
            btn.setTextColor(Color.BLACK);
        }

    }
}
