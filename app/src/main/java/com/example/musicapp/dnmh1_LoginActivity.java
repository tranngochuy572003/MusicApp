package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.adapter.dnmh1_MyViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dnmh1_LoginActivity extends AppCompatActivity {

    private TabLayout nTabLayout;
    private ImageView btnSetting;
    private CardView btnLogin;
    private DatabaseReference mDatabase;


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


        btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(v -> {
            Intent launchActivity2 = new Intent(dnmh1_LoginActivity.this, dnmh6_SettingActivity.class);
            startActivity(launchActivity2);
        });


        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            Intent launchActivity2 = new Intent(dnmh1_LoginActivity.this, dkmh1_DangkyActivity.class);
            startActivity(launchActivity2);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference userRef = mDatabase.child("save_data").child("User").child(userId);
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String userName = dataSnapshot.child("Name").getValue(String.class);
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
        }
    }


}
