package com.example.musicapp.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;
import com.example.musicapp.adapter.MusicAdapter;
import com.example.musicapp.model.Music;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAlbumFragment extends Fragment {
    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private List<Music> musicList;
    private List<Music> filteredList; // Danh sách bài hát đã lọc
    private DatabaseReference database;
    private EditText editTextSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout cho Fragment
        return inflater.inflate(R.layout.dnmh1_favourite_album_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Ánh xạ các thành phần giao diện
        recyclerView = view.findViewById(R.id.recycler_view);
        editTextSearch = view.findViewById(R.id.editTextSearch);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Khởi tạo danh sách và adapter
        musicList = new ArrayList<>();
        filteredList = new ArrayList<>();
        musicAdapter = new MusicAdapter(filteredList);
        recyclerView.setAdapter(musicAdapter);

        // Kết nối Firebase
        database = FirebaseDatabase.getInstance().getReference("music");

        // Lắng nghe thay đổi từ Firebase
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                musicList.clear();
                for (DataSnapshot musicSnapshot : snapshot.getChildren()) {
                    Music music = musicSnapshot.getValue(Music.class);
                    musicList.add(music);
                }
                filteredList.addAll(musicList); // Sao chép danh sách gốc vào danh sách đã lọc
                musicAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG", "onCancelled: " + error.toString());
            }
        });

        // Lắng nghe thay đổi trong EditText
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterSongs(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterSongs(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(musicList); // Nếu không có từ khóa, hiển thị toàn bộ danh sách
        } else {
            for (Music music : musicList) {
                if (music.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(music); // Thêm bài hát vào danh sách đã lọc nếu trùng khớp
                }
            }
        }
        musicAdapter.notifyDataSetChanged(); // Cập nhật adapter
    }

}
