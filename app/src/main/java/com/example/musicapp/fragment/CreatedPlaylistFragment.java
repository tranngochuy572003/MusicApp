package com.example.musicapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicapp.CreatePlaylistActivity;
import com.example.musicapp.R;

public class CreatedPlaylistFragment extends Fragment {
    private ImageView btnAddPlaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dnmh1_created_playlist_fragement, container, false);

        btnAddPlaylist = rootView.findViewById(R.id.btnAddPlaylist);
        btnAddPlaylist.setOnClickListener(v -> {
            Intent launchActivity1 = new Intent(requireActivity(), CreatePlaylistActivity.class);
            startActivity(launchActivity1);
        });

        return rootView;
    }
}
