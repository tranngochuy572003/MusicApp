package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.model.Music;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ttcnmh2_thongtincanhanActivity extends AppCompatActivity {
    TextView suadoiman2,Numan2,chitietID,ctsdtman8,ctsdtman2,ctemailman2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttcnmh2_thongtincanhan); // Tệp XML giao diện của màn hình thông tin cá nhân

        // Liên kết ImageView với mã Java và thiết lập sự kiện click cho mỗi ImageView



        // ImageView Quay phải 6 - chuyển sang Activity DienTieuSuActivity
        ImageView quayPhai6 = findViewById(R.id.quayphai6);
        quayPhai6.setOnClickListener(view -> {
            Intent intent = new Intent(ttcnmh2_thongtincanhanActivity.this, ttcnmh4_dientieusuActivity.class);
            startActivity(intent);
        });





        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/save_data/User/" + userId);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String uuid = userSnapshot.getKey();
                    String phone = snapshot.child("phone").getValue(String.class);
                    String dob = snapshot.child("dob").getValue(String.class);
                    String gender = snapshot.child("gender").getValue(String.class);
                    String name = snapshot.child("username").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);

                    suadoiman2 = findViewById(R.id.suadoiman2);
                    String formattedDob = dob
                            .replace("Ngày ", "")       // Bỏ chữ "ngày "
                            .replace("tháng ", "/")     // Thay "tháng " bằng "/"
                            .replace("năm ", "/")       // Thay "năm " bằng "/"
                            .trim();
                    suadoiman2.setText(formattedDob);

                    Numan2 = findViewById(R.id.Numan2);
                    Numan2.setText(gender);

                    chitietID = findViewById(R.id.chitietID);
                    chitietID.setText(userId.substring(0, 7));

                    ctsdtman8 = findViewById(R.id.ctsdtman8);
                    ctsdtman8.setText(phone);

                    ctsdtman2 = findViewById(R.id.ctsdtman2);
                    ctsdtman2.setText(name);

                    ctemailman2 = findViewById(R.id.ctemailman2);
                    if (email!=null){
                        ctemailman2.setText(email);
                    }else{
                        ctemailman2.setText("Thêm email");
                    }

                    // ImageView Quay phải 5 - chuyển sang Activity DienTenActivity
                    ImageView quayPhai5 = findViewById(R.id.quayphai5);
                    quayPhai5.setOnClickListener(view -> {
                        Intent intent = new Intent(ttcnmh2_thongtincanhanActivity.this, ttcnmh3_dientenActivity.class);
                        intent.putExtra("ten",name);
                        startActivity(intent);
                    });

                    ImageView quayPhai4 = findViewById(R.id.quayphai4);
                    quayPhai4.setOnClickListener(view -> {
                        Intent intent = new Intent(ttcnmh2_thongtincanhanActivity.this, ttcnmh5_chongioitinhActivity.class);
                        intent.putExtra("gioitinh",gender);

                        startActivity(intent);
                    });

                    ImageView quayPhai8 = findViewById(R.id.quayphai8);
                    quayPhai8.setOnClickListener(view -> {
                        Intent intent = new Intent(ttcnmh2_thongtincanhanActivity.this, ttcnmh6_doisodienthoaiActivity.class);
                        intent.putExtra("dienthoai",phone);

                        startActivity(intent);
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}
