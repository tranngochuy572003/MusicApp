package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dnmh8_loginwithemailorphoneActivity extends AppCompatActivity {
    private LinearLayout tabEmailContent, tabPhoneContent;
    private Button tabEmail, tabPhone;
    EditText phone_input;
    EditText phone_password_input;
    Spinner country_code_spinner;
    String phone;
    String password;
    Button login_button;
    String onlyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnmh8_loginwithemailorphone);

        tabEmailContent = findViewById(R.id.tabEmailContent);
        tabPhoneContent = findViewById(R.id.tabPhoneContent);
        tabEmail = findViewById(R.id.tabEmail);
        tabPhone = findViewById(R.id.tabPhone);
        login_button = findViewById(R.id.login_button);
        country_code_spinner=findViewById(R.id.country_code_spinner);

        tabEmail.setOnClickListener(v -> {
            // Hiển thị nội dung Email, ẩn nội dung Phone
            tabEmailContent.setVisibility(View.VISIBLE);
            tabPhoneContent.setVisibility(View.GONE);

            // Cập nhật màu nền cho các tab để dễ nhận biết
            tabEmail.setBackgroundColor(getResources().getColor(R.color.purple));
            tabPhone.setBackgroundColor(getResources().getColor(R.color.gray_701));
        });

        tabPhone.setOnClickListener(v -> {
            // Hiển thị nội dung Phone, ẩn nội dung Email
            tabPhoneContent.setVisibility(View.VISIBLE);
            tabEmailContent.setVisibility(View.GONE);

            // Cập nhật màu nền cho các tab
            tabPhone.setBackgroundColor(getResources().getColor(R.color.purple));
            tabEmail.setBackgroundColor(getResources().getColor(R.color.gray_701));
        });

        login_button.setOnClickListener(v -> {
            String countryCode = country_code_spinner.getSelectedItem().toString();
            onlyCode = countryCode.substring(countryCode.lastIndexOf(" ") + 1);

            phone_input = findViewById(R.id.phone_input);
            phone = onlyCode + phone_input.getText().toString();
            phone_password_input = findViewById(R.id.phone_password_input);
            password = phone_password_input.getText().toString();
            signInUser(phone, password);
        });

        // Mặc định hiển thị tab Email khi ứng dụng mở
        tabEmailContent.setVisibility(View.VISIBLE);
        tabPhoneContent.setVisibility(View.GONE);
    }


    private void signInUser(String phone, String password) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("/save_data/User");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String uuid = userSnapshot.getKey();
                    String phonedb = snapshot.child(uuid).child("phone").getValue(String.class);
                    String passworddb = snapshot.child(uuid).child("password").getValue(String.class);
                    if (phonedb != null && phonedb.equals(phone)) {
                        if (passworddb != null && passworddb.equals(password)) {
                            String usernamedb = snapshot.child(uuid).child("username").getValue(String.class);
                            String dobdb = snapshot.child(uuid).child("dob").getValue(String.class);
                            String gioitinh = snapshot.child(uuid).child("gender").getValue(String.class);

                            User user = new User(uuid, usernamedb, null, dobdb, phone, password, gioitinh,null,null);
                            Intent launchActivity5 = new Intent(dnmh8_loginwithemailorphoneActivity.this, dnmh4_checkphoneActivity.class);
                            launchActivity5.putExtra("userFromDangNhapSDT", user);

                            startActivity(launchActivity5);
                            return;
                        } else {
                            Toast.makeText(dnmh8_loginwithemailorphoneActivity.this, "Sai so dien thoai hoac mat khau", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
                Toast.makeText(dnmh8_loginwithemailorphoneActivity.this, "Sai so dien thoai hoac mat khau", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Database error: " + error.getMessage());
            }
        });

    }

}

