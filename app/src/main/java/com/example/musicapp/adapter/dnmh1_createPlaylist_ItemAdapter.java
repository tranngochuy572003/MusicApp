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
        holder.nameTextView.setText(item.getName());
        holder.priceTextView.setText(String.valueOf(item.getCreatedDate()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_name);
            priceTextView = itemView.findViewById(R.id.item_price);
        }
    }
}
