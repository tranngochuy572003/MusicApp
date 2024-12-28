package com.example.musicapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dkmh3_dangkyemailActivity extends AppCompatActivity{

     private Button btnRegister; // Button "ĐĂNG KÝ MỚI"
        private TextView tabPhone; // Tab chuyển sang đăng ký bằng số điện thoại
        private EditText editTextPassword; // Trường nhập mật khẩu
        private ImageView imgTogglePassword; // Biểu tượng toggle mật khẩu
        private EditText editTextConfirmPassword; // Trường nhập lại mật khẩu
        private ImageView imgToggleConfirmPassword; // Biểu tượng toggle mật khẩu cho "Nhập lại mật khẩu"
        private CheckBox termsCheckbox; // Checkbox đồng ý điều khoản

        // Biến để theo dõi trạng thái hiển thị mật khẩu
        private boolean isPasswordVisible = false;
        private boolean isConfirmPasswordVisible = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Liên kết với layout màn hình đăng ký email
            setContentView(R.layout.dkmh3_dangkyemail);

            // Ánh xạ các View
            btnRegister = findViewById(R.id.btnRegister);
            tabPhone = findViewById(R.id.tabPhone);
            editTextPassword = findViewById(R.id.password);
            imgTogglePassword = findViewById(R.id.imgTogglePassword);
            editTextConfirmPassword = findViewById(R.id.confirmPassword); // Trường nhập lại mật khẩu
            imgToggleConfirmPassword = findViewById(R.id.imgToggleConfirmPassword); // Biểu tượng toggle cho nhập lại mật khẩu
            termsCheckbox = findViewById(R.id.agreePolicy); // Checkbox đồng ý điều khoản

            // Xử lý sự kiện toggle hiển thị mật khẩu
            imgTogglePassword.setOnClickListener(v -> {
                if (isPasswordVisible) {
                    // Ẩn mật khẩu
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imgTogglePassword.setImageResource(R.drawable.dkmh2_eye_off); // Đổi icon về "ẩn"
                } else {
                    // Hiển thị mật khẩu
                    editTextPassword.setTransformationMethod(null);
                    imgTogglePassword.setImageResource(R.drawable.dkmh2_eye_open); // Đổi icon về "hiện"
                }
                isPasswordVisible = !isPasswordVisible;

                // Đặt lại vị trí con trỏ
                editTextPassword.setSelection(editTextPassword.getText().length());
            });

            // Xử lý sự kiện toggle hiển thị mật khẩu cho trường "Nhập lại mật khẩu"
            imgToggleConfirmPassword.setOnClickListener(v -> {
                if (isConfirmPasswordVisible) {
                    editTextConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imgToggleConfirmPassword.setImageResource(R.drawable.dkmh2_eye_off); // Đổi icon về "ẩn"
                } else {
                    editTextConfirmPassword.setTransformationMethod(null);
                    imgToggleConfirmPassword.setImageResource(R.drawable.dkmh2_eye_open); // Đổi icon về "hiện"
                }
                isConfirmPasswordVisible = !isConfirmPasswordVisible;

                // Đặt lại vị trí con trỏ
                editTextConfirmPassword.setSelection(editTextConfirmPassword.getText().length());
            });

            // Xử lý sự kiện khi click vào "ĐĂNG KÝ MỚI"
            btnRegister.setOnClickListener(v -> {
                if (termsCheckbox.isChecked()) {
                    // Chuyển sang màn hình đăng ký thành công
                    Intent intent = new Intent(dkmh3_dangkyemailActivity.this, dkmh5_dangkythanhcongActivity.class);
                    startActivity(intent);
                } else {
                    // Hiển thị thông báo nếu chưa tick vào checkbox
                    Toast.makeText(dkmh3_dangkyemailActivity.this, "Vui lòng đồng ý các điều khoản về chính sách bảo mật và thỏa thuận sử dụng.", Toast.LENGTH_LONG).show();
                }
            });

            tabPhone.setOnClickListener(v -> {
                Intent intent = new Intent(dkmh3_dangkyemailActivity.this, dkmh3_dangkysdtActivity.class);
                startActivity(intent);
                finish();

            });
        }
    }
