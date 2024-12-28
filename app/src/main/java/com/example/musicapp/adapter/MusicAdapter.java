package com.example.musicapp.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.MusicPlayerActivity;
import com.example.musicapp.R;
import com.example.musicapp.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private List<Music> musicList;

    public static class MusicViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView artist;

        public MusicViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            artist = itemView.findViewById(R.id.text_artist);
        }
    }

    public MusicAdapter(List<Music> musicList) {
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Music music = musicList.get(position);
        holder.title.setText(music.getTitle());
        holder.artist.setText(music.getArtist());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MusicPlayerActivity.class);
            intent.putExtra("musicList", (ArrayList<Music>) musicList);
            intent.putExtra("songIndex", position);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }
}