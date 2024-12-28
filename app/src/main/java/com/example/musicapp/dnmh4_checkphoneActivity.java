package com.example.musicapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.model.User;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class dnmh4_checkphoneActivity extends AppCompatActivity {
    private TextView tvTimer;
    private TextView tvResend;
    private FirebaseAuth mAuth;
    private String verificationId;

    private String phoneFromSignIn;
    private String phoneFromDangKySDT;
    private String otp;
    String passWordFromDangKySDT;
    String userNameFromDangKySDT;
    User userFromDangNhapSDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnmh4_checkphone);

        Intent intent = getIntent();
        userFromDangNhapSDT = (User) intent.getSerializableExtra("userFromDangNhapSDT");

        mAuth = FirebaseAuth.getInstance();
        tvTimer = findViewById(R.id.tvTimer);
        tvResend = findViewById(R.id.tvResend);
        phoneFromSignIn = getIntent().getStringExtra("phoneFromSignIn");
        phoneFromDangKySDT = getIntent().getStringExtra("phoneFromDangKySDT");
        passWordFromDangKySDT = getIntent().getStringExtra("passWordFromDangKySDT");
        userNameFromDangKySDT = getIntent().getStringExtra("userNameFromDangKySDT");


        TextView phoneTextView = findViewById(R.id.tvDescription);
        phoneTextView.setText(phoneFromSignIn);

        startCountDown();

        EditText otp1 = findViewById(R.id.otp1);
        EditText otp2 = findViewById(R.id.otp2);
        EditText otp3 = findViewById(R.id.otp3);
        EditText otp4 = findViewById(R.id.otp4);
        EditText otp5 = findViewById(R.id.otp5);
        EditText otp6 = findViewById(R.id.otp6);

        List<EditText> otpFields = Arrays.asList(otp1, otp2, otp3, otp4, otp5, otp6);


        Button btnVerifyCode = findViewById(R.id.btnVerifyCode);
        btnVerifyCode.setOnClickListener(v -> {
            StringBuilder otpCode = new StringBuilder();
            for (EditText otpField : otpFields) {
                otpCode.append(otpField.getText().toString().trim());
            }
            otp = otpCode.toString();
            if (!otp.isEmpty()) {
                verifyCode(otp);
            } else {
                Toast.makeText(this, "Please enter verification code", Toast.LENGTH_SHORT).show();
            }
        });

        addTextWatchers(otpFields);

        if (phoneFromSignIn != null) {
            sendVerificationCode(phoneFromSignIn);
        } else if (phoneFromDangKySDT != null) {
            sendVerificationCode(phoneFromDangKySDT);
        }else if (userFromDangNhapSDT != null) {
            sendVerificationCode(userFromDangNhapSDT.getPhone());
        }
    }


    private void startCountDown() {
        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(" sau " + millisUntilFinished / 1000 + " giây");
            }

            @Override
            public void onFinish() {
                tvResend.setTextColor(Color.parseColor("#4DC4C4"));
                tvResend.setOnClickListener(v -> {
                    if (phoneFromSignIn != null) {
                        sendVerificationCode(phoneFromSignIn);
                    } else if (phoneFromDangKySDT != null) {
                        sendVerificationCode(phoneFromDangKySDT);
                    }
                    Toast.makeText(dnmh4_checkphoneActivity.this, "Verification code resent.", Toast.LENGTH_SHORT).show();
                });

            }
        }.start();
    }

    public void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(callbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential credential) {
                    signInWithPhoneAuthCredential(credential);
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(dnmh4_checkphoneActivity.this, "Verification Failed: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCodeSent(String verificationId,
                                       PhoneAuthProvider.ForceResendingToken token) {
                    dnmh4_checkphoneActivity.this.verificationId = verificationId;
                    Toast.makeText(dnmh4_checkphoneActivity.this, "Code Sent", Toast.LENGTH_SHORT).show();
                }
            };

    public void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        if (credential != null) {
            signInWithPhoneAuthCredential(credential);
        } else {
            Toast.makeText(this, "Mã xác minh không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = task.getResult().getUser();
                        if (user != null) {
                            User userSaved = new User(user.getUid(), userNameFromDangKySDT, null, null, user.getPhoneNumber(), passWordFromDangKySDT,null,null,null);
                            if (phoneFromSignIn != null) {
                                Intent launchActivity4 = new Intent(dnmh4_checkphoneActivity.this, dnmh1_LoginActivity.class);
                                startActivity(launchActivity4);
                            }
                            if (userFromDangNhapSDT != null) {
                                Intent launchActivity6 = new Intent(dnmh4_checkphoneActivity.this, dnmh1_LoginActivity.class);
                                startActivity(launchActivity6);
                            }
                            else{
                                Intent launchActivity4 = new Intent(dnmh4_checkphoneActivity.this, dkmh11_XulyNgaySinhNhatActivity.class);
                                launchActivity4.putExtra("user", userSaved);
                                startActivity(launchActivity4);
                                finish();                            }
                        }
                    } else {
                        Toast.makeText(dnmh4_checkphoneActivity.this, "Verification Failed",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void addTextWatchers(List<EditText> otpFields) {
        for (int i = 0; i < otpFields.size(); i++) {
            final int index = i;
            EditText currentField = otpFields.get(index);

            currentField.addTextChangedListener(new TextWatcher() {
                private String previousText = "";

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    previousText = s.toString(); // Save previous text before change
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 1) {
                        // If user enters a character, move to the next field
                        if (index < otpFields.size() - 1) {
                            otpFields.get(index + 1).requestFocus();
                        }
                    } else if (s.length() == 0 && previousText.length() == 1) {
                        // If user deletes a character, move back to the previous field
                        if (index > 0) {
                            otpFields.get(index - 1).requestFocus();
                            otpFields.get(index - 1).setText(""); // Clear the previous field
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }
}

