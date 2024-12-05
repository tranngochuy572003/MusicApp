package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {
    private ConstraintLayout containerLayout;

    private TabLayout nTabLayout;
    private ImageView btnSetting ;


    private ViewPager nViewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnmh1_login_view);

        EdgeToEdge.enable(this);
        nTabLayout = findViewById(R.id.tabLayout);
        nViewPager = findViewById(R.id.viewPager);

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);


        nViewPager.setAdapter(myViewPagerAdapter);
        nTabLayout.setupWithViewPager(nViewPager);


        btnSetting =findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchActivity1= new Intent(LoginActivity.this,SettingActivity.class);
                startActivity(launchActivity1);
            }
        });


    }

}
