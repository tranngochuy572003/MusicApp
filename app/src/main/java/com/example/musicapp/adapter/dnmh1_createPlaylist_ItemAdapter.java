package com.example.musicapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;
import com.example.musicapp.model.Playlist;

import java.util.List;

public class dnmh1_createPlaylist_ItemAdapter extends RecyclerView.Adapter<dnmh1_createPlaylist_ItemAdapter.ItemViewHolder> {

    private List<Playlist> itemList;
    private OnItemClickListener onItemClickListener;

    // Interface for item click
    public interface OnItemClickListener {
        void onItemClick(Playlist playlist);
    }

    // Constructor to pass the listener
    public dnmh1_createPlaylist_ItemAdapter(List<Playlist> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.onItemClickListener = listener;
    }

    public dnmh1_createPlaylist_ItemAdapter(List<Playlist> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dnmh1_item_created_playlist, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Playlist item = itemList.get(position);
        holder.nameTextView.setText("Tên : " + item.getName());
        holder.createdAt.setText("Ngày tạo : " + item.getCreated_at());
        // Set the click listener for each item
        holder.itemView.setOnClickListener(v -> {
            // Trigger the listener when an item is clicked
            if (onItemClickListener != null && item.getMusicList() != null) {
                onItemClickListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, createdAt;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_name);
            createdAt = itemView.findViewById(R.id.created_at);

        }
    }
}
