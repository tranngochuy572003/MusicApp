package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.adapter.dnmh1_MyViewPagerAdapter;
import com.example.musicapp.model.Music;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class dnmh1_LoginActivity extends AppCompatActivity {

    private TabLayout nTabLayout;
    private ImageView btnSetting;
    private CardView btnLogin;
    private DatabaseReference mDatabase;
    //    User user;
    TextView navForYouText;
    Music music;
    private ViewPager nViewPager;
    private dnmh1_MyViewPagerAdapter dnmh1MyViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnmh1_login_view);

        EdgeToEdge.enable(this);

        nTabLayout = findViewById(R.id.tabLayout);
        nViewPager = findViewById(R.id.viewPager);

        dnmh1MyViewPagerAdapter = new dnmh1_MyViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);


        nViewPager.setAdapter(dnmh1MyViewPagerAdapter);
        nTabLayout.setupWithViewPager(nViewPager);


        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            Intent launchActivity2 = new Intent(dnmh1_LoginActivity.this, dkmh1_DangkyActivity.class);
            startActivity(launchActivity2);
        });

        navForYouText = findViewById(R.id.navForYouText);
        navForYouText.setOnClickListener(v -> {
            Intent launchActivity2 = new Intent(dnmh1_LoginActivity.this, dnmh1_LoginActivity.class);
            startActivity(launchActivity2);
        });

        btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(v -> {
            Intent launchActivity2 = new Intent(dnmh1_LoginActivity.this, dnmh6_SettingActivity.class);
            startActivity(launchActivity2);
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference musicRef = mDatabase.child("save_data").child("User").child(userId);
            Intent intent = getIntent();
            music = (Music) intent.getSerializableExtra("loveSong");

            if (music != null) {
                musicRef.child("favouriteSong").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Nếu đã có bài hát yêu thích
                        List<Music> favouriteSongs = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Music existingMusic = snapshot.getValue(Music.class);
                            if (existingMusic != null) {
                                favouriteSongs.add(existingMusic); // Thêm bài hát cũ vào danh sách
                            }
                        }
                        // Thêm bài hát mới vào danh sách
                        favouriteSongs.add(music);

                        musicRef.child("favouriteSong").setValue(favouriteSongs)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Log.d("dnmh1_LoginActivity", "Favorite song added successfully");
                                        Toast.makeText(dnmh1_LoginActivity.this, "Favorite song updated!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.e("dnmh1_LoginActivity", "Failed to update music", task.getException());
                                        Toast.makeText(dnmh1_LoginActivity.this, "Failed to update favorite song.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("dnmh1_LoginActivity", "loadUserName:onCancelled", databaseError.toException());
                    }
                });
            }




            DatabaseReference userRef2 = mDatabase.child("save_data").child("User").child(userId);
            userRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String userName = dataSnapshot.child("username").getValue(String.class);
                        if (userName != null) {
                            TextView userNameTextView = findViewById(R.id.txtLogin);
                            userNameTextView.setText(userName);
                            btnLogin.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("dnmh1_LoginActivity", "loadUserName:onCancelled", databaseError.toException());
                }
            });
        } else {
            btnLogin.setVisibility(View.VISIBLE);
            btnSetting.setVisibility(View.GONE);
        }

    }


}
