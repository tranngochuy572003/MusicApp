package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.adapter.MusicAdapter;
import com.example.musicapp.model.Music;
import com.example.musicapp.model.Playlist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class chonPlaylistActivity extends AppCompatActivity {
    private List<Playlist> itemList;
    FirebaseUser currentUser;
    Music currentSong;
    List<String> selectedPlaylists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chonplaylist);

        currentSong = (Music) getIntent().getSerializableExtra("currentSong");

        itemList = new ArrayList<>();

        LinearLayout checkboxContainer = findViewById(R.id.checkbox_container);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/save_data/User/" + userId + "/Playlists");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                checkboxContainer.removeAllViews(); // Clear previous checkboxes

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Playlist playlist = snapshot.getValue(Playlist.class);
                    if (playlist != null) {
                        itemList.add(playlist);

                    }
                }
                selectedPlaylists = new ArrayList<>();

                for (Playlist playlist : itemList) {
                    CheckBox checkBox = new CheckBox(chonPlaylistActivity.this);
                    checkBox.setText(playlist.getName());
                    selectedPlaylists.add(playlist.getName());
                    checkboxContainer.addView(checkBox);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected checkboxes
                for (String selectedPlaylist : selectedPlaylists) {

                    DatabaseReference userPlaylistRef = FirebaseDatabase.getInstance()
                            .getReference("/save_data/User/" + currentUser.getUid() + "/Playlists");
                    userPlaylistRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String playlistId = dataSnapshot.getKey();
                                String playlistName = snapshot.child(playlistId).child("name").getValue(String.class);

                                if (selectedPlaylist.equals(playlistName)) {
                                    Playlist playlist = dataSnapshot.getValue(Playlist.class); // Assuming Playlist is a model class
                                    addSongToPlaylist(playlist, currentSong);
                                    Toast.makeText(chonPlaylistActivity.this, "Thêm bài hát thành công", Toast.LENGTH_SHORT).show();

                                    Intent launchActivity = new Intent(chonPlaylistActivity.this, dnmh1_LoginActivity.class);
                                    startActivity(launchActivity);
                                    break;
                                }
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w("dnmh1_LoginActivity", "loadUserName:onCancelled", databaseError.toException());
                        }
                    });

                }


            }
        });

    }

    private void addSongToPlaylist(Playlist playlist, Music music) {
        if (playlist.getMusicList() != null) {
            playlist.getMusicList().add(music);
        }else{
            playlist.setMusicList(List.of(music));
        }
        DatabaseReference userPlaylistRef = FirebaseDatabase.getInstance()
                .getReference("/save_data/User/" + currentUser.getUid() + "/Playlists/" + playlist.getId());
        userPlaylistRef.setValue(playlist).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("Firebase", "Song added successfully to the playlist.");
            } else {
                Log.w("Firebase", "Failed to add song to playlist.");
            }
        });
    }

}
