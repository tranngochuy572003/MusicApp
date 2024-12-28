package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class dkmh3_dangkysdtActivity extends AppCompatActivity {
    private TextView tabEmail; // Tab "Email"
    private Button btnSendCode; // Nút "GỬI MÃ"
    private EditText etPhoneNumber; // Trường nhập số điện thoại
    Spinner countryCodeSpinner;
    EditText passwordEditText;
    EditText confirmPasswordEditText;
    CheckBox agreePolicyCheckBox;
    EditText userNameEditText;
    String userName;
    String password;
    String onlyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dkmh3_dangkysdt);

        countryCodeSpinner = findViewById(R.id.country_code_spinner);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        agreePolicyCheckBox = findViewById(R.id.agreePolicy);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        tabEmail = findViewById(R.id.tabEmail);
        btnSendCode = findViewById(R.id.btnSendCode);
        userNameEditText = findViewById(R.id.userName);


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

                // Lấy số điện thoại và mã quốc gia
                String phoneNumber = etPhoneNumber.getText().toString();
                String countryCode = countryCodeSpinner.getSelectedItem().toString();
                onlyCode = countryCode.substring(countryCode.lastIndexOf(" ") + 1);

                // Lấy mật khẩu và mật khẩu xác nhận
                password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Kiểm tra người dùng đã đồng ý các điều khoản chưa
                boolean isAgreed = agreePolicyCheckBox.isChecked();

                userName = userNameEditText.getText().toString();


                if (phoneNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(dkmh3_dangkysdtActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(dkmh3_dangkysdtActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else if (!isAgreed) {
                    Toast.makeText(dkmh3_dangkysdtActivity.this, "Vui lòng đồng ý với các điều khoản", Toast.LENGTH_SHORT).show();
                }
                checkPhoneNumberAndRegister(onlyCode + phoneNumber);

            }
        });


    }

    private void checkPhoneNumberAndRegister(String phoneNumber) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("/save_data/User");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String uuid = userSnapshot.getKey();
                    String phone = snapshot.child(uuid).child("phone").getValue(String.class);
                    if (phone != null && phone.equals(phoneNumber)) {
                        Intent launchActivity5 = new Intent(dkmh3_dangkysdtActivity.this, dnmh1_LoginActivity.class);
                        startActivity(launchActivity5);
                        Toast.makeText(dkmh3_dangkysdtActivity.this, "So dien thoai da duoc dang ki. Vui long dang nhap", Toast.LENGTH_SHORT).show();
                        finish();
                        return;

                    }
                }
                Intent intent = new Intent(dkmh3_dangkysdtActivity.this, dnmh4_checkphoneActivity.class);
                intent.putExtra("phoneFromDangKySDT",  phoneNumber);
                intent.putExtra("passWordFromDangKySDT", password);
                intent.putExtra("userNameFromDangKySDT", userName);
                startActivity(intent);
                finish();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Database error: " + error.getMessage());
            }
        });

    }

}
