package com.example.musicapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class dkmh11_XulyNgaySinhNhatActivity extends AppCompatActivity {
    private String selectedBirthdate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dkmh11_nhapngaysinh);

        EditText birthdayEditText = findViewById(R.id.birthdayEditText);
        Button btnContinue = findViewById(R.id.btnContinue);
        TextView btnSkip = findViewById(R.id.btnSkip);

        birthdayEditText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(
                    dkmh11_XulyNgaySinhNhatActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        selectedBirthdate = "Ngày " + selectedDay + " tháng " + (selectedMonth + 1) + " năm " + selectedYear;
                        birthdayEditText.setText(selectedBirthdate);
                    },
                    year, month, day
            );

            datePicker.show();

            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!selectedBirthdate.isEmpty()) {
                        Intent intent = new Intent(dkmh11_XulyNgaySinhNhatActivity.this, dnmh5_entersexActivity.class);
                        intent.putExtra("birthdate", selectedBirthdate);
                        startActivity(intent);
                    } else {
                        Toast.makeText(dkmh11_XulyNgaySinhNhatActivity.this, "Vui lòng chọn ngày sinh trước khi tiếp tục.", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            btnSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(dkmh11_XulyNgaySinhNhatActivity.this, dnmh5_entersexActivity.class);
                    startActivity(intent);

                }
            });
        });

    }
}
