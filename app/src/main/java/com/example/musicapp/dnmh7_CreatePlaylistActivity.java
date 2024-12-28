package com.example.musicapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class dnmh7_CreatePlaylistActivity extends AppCompatActivity {
    private EditText editTextPlaylistName;
    private Button buttonCreatePlaylist;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnmh7_createplaylist);
        editTextPlaylistName = findViewById(R.id.playlistNameInput);
        buttonCreatePlaylist = findViewById(R.id.createPlaylistButton);

        buttonCreatePlaylist.setEnabled(false);
        editTextPlaylistName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override                // Kích hoạt nút nếu tên không rỗng

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Kích hoạt nút nếu tên không rỗng
                buttonCreatePlaylist.setEnabled(!s.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        buttonCreatePlaylist.setOnClickListener(v -> {
            String playlistName = editTextPlaylistName.getText().toString().trim();
            if (!playlistName.isEmpty()) {
                savePlaylistToFirebase(playlistName);
                Toast.makeText(this, "Đã tạo playlist: " + playlistName, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void savePlaylistToFirebase(String playlistName) {
        String playlistId = UUID.randomUUID().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());
        HashMap<String, Object> playlistData = new HashMap<>();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        playlistData.put("id", playlistId);
        playlistData.put("name", playlistName);
        playlistData.put("created_at", currentDate);

        DatabaseReference userPlaylistRef = FirebaseDatabase.getInstance()
                .getReference("/save_data/User/" + currentUser.getUid() + "/Playlists");
        userPlaylistRef.child(playlistId).setValue(playlistData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Playlist đã được lưu!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Lỗi khi lưu playlist: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
