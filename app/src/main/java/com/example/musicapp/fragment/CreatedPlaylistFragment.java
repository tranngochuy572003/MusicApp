package com.example.musicapp.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.MainActivity;
import com.example.musicapp.R;
import com.example.musicapp.adapter.MusicAdapter;
import com.example.musicapp.adapter.dnmh1_createPlaylist_ItemAdapter;
import com.example.musicapp.dkmh1_DangkyActivity;
import com.example.musicapp.dnmh1_LoginActivity;
import com.example.musicapp.dnmh1_created_playlist_fragementActivity;
import com.example.musicapp.dnmh4_checkphoneActivity;
import com.example.musicapp.dnmh7_CreatePlaylistActivity;
import com.example.musicapp.model.Playlist;
import com.example.musicapp.model.User;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreatedPlaylistFragment extends Fragment implements dnmh1_createPlaylist_ItemAdapter.OnItemClickListener {

    ImageView btnAddPlaylist;
    ImageView addIcon;
    private RecyclerView recyclerView;
    private List<Playlist> itemList;
    private dnmh1_createPlaylist_ItemAdapter adapter;
    private DatabaseReference databaseReference;
    MusicAdapter musicAdapter;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        View rootView = inflater.inflate(R.layout.dnmh1_created_playlist_fragement, container, false);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Đăng nhập yêu cầu")
                    .setMessage("Vui lòng đăng nhập để tiếp tục.")
                    .setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent loginIntent = new Intent(getActivity(), dkmh1_DangkyActivity.class);
                            startActivity(loginIntent);
                        }
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        } else {
            btnAddPlaylist = rootView.findViewById(R.id.btnAddPlaylist);
            btnAddPlaylist.setOnClickListener(v -> {
                Intent launchActivity1 = new Intent(requireActivity(), dnmh7_CreatePlaylistActivity.class);
                startActivity(launchActivity1);
            });

            addIcon = rootView.findViewById(R.id.addIcon);
            addIcon.setOnClickListener(v -> {
                Intent launchActivity2 = new Intent(requireActivity(), dnmh7_CreatePlaylistActivity.class);
                startActivity(launchActivity2);
            });

            recyclerView = rootView.findViewById(R.id.item_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            itemList = new ArrayList<>();
            adapter = new dnmh1_createPlaylist_ItemAdapter(itemList, this);
            recyclerView.setAdapter(adapter);

            String userId = currentUser.getUid();

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/save_data/User/" + userId + "/Playlists");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    itemList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Playlist playlist = snapshot.getValue(Playlist.class);
                        if (playlist != null) {
                            itemList.add(playlist);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        return rootView;
    }


    @Override
    public void onItemClick(Playlist playlist) {
        if(playlist!=null){
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            musicAdapter = new MusicAdapter(playlist.getMusicList());
            recyclerView.setAdapter(musicAdapter);


        }
        else {
            Log.e("CreatedPlaylistFragment", "Playlist is null");
            Toast.makeText(requireActivity(), "Playlist data is unavailable", Toast.LENGTH_SHORT).show();
        }

    }
}
