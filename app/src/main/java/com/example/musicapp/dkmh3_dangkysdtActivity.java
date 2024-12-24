package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dkmh3_dangkysdtActivity extends AppCompatActivity {
    private TextView tabEmail; // Tab "Email"
    private Button btnSendCode; // Nút "GỬI MÃ"
    private EditText etPhoneNumber; // Trường nhập số điện thoại
    private CheckBox termsCheckbox; // Chec
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dkmh3_dangkysdt);

        // Ánh xạ các thành phần
        tabEmail = findViewById(R.id.tabEmail);
        btnSendCode = findViewById(R.id.btnSendCode);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        termsCheckbox = findViewById(R.id.agreePolicy); // Ánh xạ checkbox từ layout


        tabEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển về màn hình "Đăng ký email"
                Intent intent = new Intent(dkmh3_dangkysdtActivity.this, dkmh3_dangkyemailActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Xử lý sự kiện nút "GỬI MÃ"
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = etPhoneNumber.getText().toString().trim();

                if (!termsCheckbox.isChecked()) {
                    Toast.makeText(dkmh3_dangkysdtActivity.this, "Vui lòng đồng ý các điều khoản về chính sách bảo mật và thỏa thuận sử dụng.", Toast.LENGTH_LONG).show();
                } else if (phoneNumber.isEmpty()) {
                    Toast.makeText(dkmh3_dangkysdtActivity.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(dkmh3_dangkysdtActivity.this, dnmh4_checkphoneActivity.class);
                    Spinner spinnerCountry = findViewById(R.id.spinnerCountry);
                    String selectedCountry = spinnerCountry.getSelectedItem().toString();
                    intent.putExtra("phoneFromSignUp", selectedCountry + phoneNumber);

                    startActivity(intent);
                }
            }
        });
    }
}
