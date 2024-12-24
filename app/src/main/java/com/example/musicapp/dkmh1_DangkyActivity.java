package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class dkmh1_DangkyActivity extends AppCompatActivity {
    private Button btnPhone;
    private Button btnPassword;
    private TextView signupText; // Khai báo TextView "Đăng ký ngay"
    private CheckBox termsCheckbox; // Khai báo CheckBox

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dkmh1_dangky);

        signupText = findViewById(R.id.signup);
        termsCheckbox = findViewById(R.id.policyCheckbox);

        // Xử lý sự kiện khi click vào "Đăng ký ngay"
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra trạng thái của checkbox
                if (termsCheckbox.isChecked()) {
                    // Chuyển sang màn hình đăng ký email
                    Intent intent = new Intent(dkmh1_DangkyActivity.this, dkmh3_dangkyemailActivity.class);
                    startActivity(intent);


                } else {
                    // Hiển thị thông báo nếu chưa tick vào checkbox
                    Toast.makeText(dkmh1_DangkyActivity.this, "Vui lòng đồng ý các điều khoản về chính sách bảo mật và thỏa thuận sử dụng.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnPhone = findViewById(R.id.btnPhone);
        btnPhone.setOnClickListener(v -> {
            Intent launchActivity = new Intent(dkmh1_DangkyActivity.this, dnmh3_enterphoneActivity.class);
            startActivity(launchActivity);
        });
        btnPassword = findViewById(R.id.btnPassword);
        btnPassword.setOnClickListener(v -> {
            Intent launchActivity2 = new Intent(dkmh1_DangkyActivity.this, dnmh8_loginwithemailorphoneActivity.class);
            startActivity(launchActivity2);
        });


    }
}
