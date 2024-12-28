package com.example.musicapp.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;
import com.example.musicapp.adapter.MusicAdapter;
import com.example.musicapp.adapter.dnmh1_createPlaylist_ItemAdapter;
import com.example.musicapp.dkmh1_DangkyActivity;
import com.example.musicapp.model.Music;
import com.example.musicapp.model.Playlist;
import com.example.musicapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavouritePlaylistFragment extends Fragment {

    private RecyclerView recyclerView;
    private dnmh1_createPlaylist_ItemAdapter adapter;
    private DatabaseReference databaseReference;
    private List<Music> favouriteSongs;
    String userId;
    private List<Music> musicList;

    MusicAdapter musicAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dnmh1_favourite_playlist_fragment, container, false);

        favouriteSongs = new ArrayList<>();
        recyclerView = rootView.findViewById(R.id.item_recycler_view_favourite); // Make sure recyclerView is initialized properly
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        musicAdapter = new MusicAdapter(favouriteSongs);
        recyclerView.setAdapter(musicAdapter);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/save_data/User/" + userId + "/favouriteSong");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        favouriteSongs.clear(); // Clear the current list
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Music music = snapshot.getValue(Music.class);
                            if (music != null) {
                                favouriteSongs.add(music); // Add to the list
                            }
                        }

                            musicAdapter.notifyDataSetChanged(); // Notify the adapter

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        return rootView;


    }
}
