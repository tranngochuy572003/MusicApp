package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dkmh5_dangkythanhcongActivity extends AppCompatActivity {

    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dkmh5_dangkythanhcong); // Liên kết với layout đăng ký thành công
        // Ánh xạ Button "ĐI ĐẾN ĐĂNG NHẬP"

        User user = (User) getIntent().getSerializableExtra("user");
        saveUserToDatabase(user);
        btnBack = findViewById(R.id.btnBack);

        // Xử lý sự kiện click
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dkmh5_dangkythanhcongActivity.this, dnmh8_loginwithemailorphoneActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveUserToDatabase(User user) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("/save_data/User/");
        usersRef.child(user.getId()).setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Lưu thông tin người dùng thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Không thể lưu thông tin: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
