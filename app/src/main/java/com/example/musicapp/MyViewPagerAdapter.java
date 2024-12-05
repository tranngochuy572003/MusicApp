package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.musicapp.fragment.CreatedPlaylistFragment;
import com.example.musicapp.fragment.FavouriteAlbumFragment;
import com.example.musicapp.fragment.FavouritePlaylistFragment;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CreatedPlaylistFragment();
            case 1:
                return new FavouritePlaylistFragment();
            case 2:
                return new FavouriteAlbumFragment();
            default:
                return new CreatedPlaylistFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0 :
                title = "Playlist đã tạo";
                break ;
            case 1:
                title = "Playlist yêu thích";
                break ;
            case 2:
                title = "Album yêu thich";
                break ;
        }
        return title;

    }
}
