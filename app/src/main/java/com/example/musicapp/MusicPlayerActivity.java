package com.example.musicapp;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.adapter.MusicAdapter;
import com.example.musicapp.model.Music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayerActivity extends AppCompatActivity {

    private TextView textSongTitle;
    private TextView textArtist;
    private ImageButton buttonPlayPause;
    private ImageButton buttonPrevious;
    private ImageButton buttonNext;
    private SeekBar seekBar;
    private ImageView imageCover;
    private ImageButton search;
    private MediaPlayer mediaPlayer;
    private List<Music> musicList = new ArrayList<>();
    private int currentSongIndex = 0;
    private Handler handler = new Handler();
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private ImageButton yeuthichButton;
    private ImageButton toitcn;
    private ImageButton add;
    Music currentSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        // Khởi tạo ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        // Các thành phần UI
        textSongTitle = findViewById(R.id.text_song_title);
        textArtist = findViewById(R.id.text_artist);
        buttonPlayPause = findViewById(R.id.button_play_pause);
        buttonPrevious = findViewById(R.id.button_previous);
        buttonNext = findViewById(R.id.button_next);
        seekBar = findViewById(R.id.seekBar);
        imageCover = findViewById(R.id.image_cover);
        search = findViewById(R.id.search_music);
        recyclerView = findViewById(R.id.recycler_view);
        yeuthichButton = findViewById(R.id.bamyeuthich);
        toitcn = findViewById(R.id.didentcanhan);
        add = findViewById(R.id.add);
        // Nhận danh sách bài hát từ Intent
        musicList = (ArrayList<Music>) getIntent().getSerializableExtra("musicList");
        currentSongIndex = getIntent().getIntExtra("songIndex", 0);

        // Thiết lập RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        musicAdapter = new MusicAdapter(musicList);
        recyclerView.setAdapter(musicAdapter);
        // Bắt đầu setup bài hát
        setupSong();

        // Thiết lập các nút
        setupButtons();

    }

    private void setupButtons() {
        buttonPlayPause.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                buttonPlayPause.setImageResource(R.drawable.playmh3_pause);
                try {
                    imageCover.clearAnimation(); // Dừng xoay
                } catch (Exception e) {
                    e.printStackTrace(); // Xử lý ngoại lệ
                }
            } else {
                mediaPlayer.start();
                buttonPlayPause.setImageResource(R.drawable.playmh3_play);
                updateSeekBar();
                try {
                    startImageRotation(); // Bắt đầu xoay khi phát nhạc
                } catch (Exception e) {
                    e.printStackTrace(); // Xử lý ngoại lệ
                }
            }
        });
        yeuthichButton.setOnClickListener(v -> {
            // Kiểm tra trạng thái của biểu tượng trái tim
            Drawable currentDrawable = yeuthichButton.getDrawable();
            Drawable likedDrawable = ContextCompat.getDrawable(this, R.drawable.heartliked);
            Drawable defaultDrawable = ContextCompat.getDrawable(this, R.drawable.playmh3_heart);

            // Kiểm tra xem currentDrawable có giống likedDrawable hay không
            if (currentDrawable != null && likedDrawable != null && currentDrawable.getConstantState() != null && currentDrawable.getConstantState().equals(likedDrawable.getConstantState())) {
                // Nếu đã thích, đổi lại thành trái tim trắng
                yeuthichButton.setImageResource(R.drawable.playmh3_heart);
                Toast.makeText(this, "Hủy thích bài hát!", Toast.LENGTH_SHORT).show();
            } else {
                // Nếu chưa thích, đổi thành trái tim đỏ
                yeuthichButton.setImageResource(R.drawable.heartliked);
                Toast.makeText(this, "Đã thích bài hát!", Toast.LENGTH_SHORT).show();

                Intent addIntent = new Intent(this, dnmh1_LoginActivity.class);
                addIntent.putExtra("loveSong", currentSong);
                startActivity(addIntent);
            }
        });

        add.setOnClickListener(v -> {
            Intent addIntent = new Intent(this, chonPlaylistActivity.class);
            addIntent.putExtra("currentSong", currentSong);  // Pass the current song object
            startActivity(addIntent);

//            // Kiểm tra trạng thái của biểu tượng add
//            Drawable currentDrawable = add.getDrawable();
//            Drawable likedDrawable = ContextCompat.getDrawable(this, R.drawable.playmh3_add);
//            Drawable defaultDrawable = ContextCompat.getDrawable(this, R.drawable.added);
//
//            // Kiểm tra xem currentDrawable có giống likedDrawable hay không
//            if (currentDrawable != null && likedDrawable != null && currentDrawable.getConstantState() != null && currentDrawable.getConstantState().equals(likedDrawable.getConstantState())) {
//                // Nếu đã thích, đổi lại thành chưa add
//                add.setImageResource(R.drawable.playmh3_add);
//                Toast.makeText(this, "Hủy thêm bài hát!", Toast.LENGTH_SHORT).show();
//            } else {
//                // Nếu chưa add, đổi thành add
//                add.setImageResource(R.drawable.added);
//                Toast.makeText(this, "Đã thêm bài hát!", Toast.LENGTH_SHORT).show();
//            }
        });


        buttonPrevious.setOnClickListener(v -> {
            if (currentSongIndex > 0) {
                currentSongIndex--;
                setupSong();
            }
        });

        buttonNext.setOnClickListener(v -> {
            if (currentSongIndex < musicList.size() - 1) {
                currentSongIndex++;
                setupSong();
            }
        });
        // Khai báo và xử lý sự kiện click
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang trang tìm kiếm
                Intent intent = new Intent(MusicPlayerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        toitcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang trang tìm kiếm
                Intent intent = new Intent(MusicPlayerActivity.this, ttcnmh2_thongtincanhanActivity.class);
                startActivity(intent);
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


    private void setupSong() {
        // Hiển thị ProgressDialog
        progressDialog.show();

        new Thread(() -> {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            currentSong = musicList.get(currentSongIndex);
            runOnUiThread(() -> {
                textSongTitle.setText(currentSong.getTitle());
                textArtist.setText(currentSong.getArtist());

            });

            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(currentSong.getUrl());
                mediaPlayer.prepare(); // Mất thời gian
                mediaPlayer.start();

                runOnUiThread(() -> {
                    buttonPlayPause.setImageResource(R.drawable.playmh3_play);
                    seekBar.setMax(mediaPlayer.getDuration());
                    updateSeekBar();
                    progressDialog.dismiss(); // Tắt ProgressDialog
                });

            } catch (IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> progressDialog.dismiss()); // Tắt ProgressDialog nếu có lỗi
            }

            mediaPlayer.setOnCompletionListener(mp -> {
                if (currentSongIndex < musicList.size() - 1) {
                    currentSongIndex++;
                    setupSong();
                }
            });
        }).start();
    }


    private void startImageRotation() {
        try {
            ObjectAnimator rotation = ObjectAnimator.ofFloat(imageCover, "rotation", 0f, 360f);
            rotation.setDuration(3000); // Thời gian xoay 3 giây
            rotation.setRepeatCount(ObjectAnimator.INFINITE); // Lặp lại vô hạn
            rotation.start();
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }
    }

    private void updateSeekBar() {
        if (mediaPlayer != null) {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(() -> updateSeekBar(), 500); // Giảm khoảng thời gian kiểm tra (500ms thay vì 1000ms)
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        progressDialog.dismiss(); // Đảm bảo tắt ProgressDialog nếu còn mở
    }


    @Override
    public void onBackPressed() {
        // Dừng nhạc nếu đang phát
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause(); // Tạm dừng nhạc
                buttonPlayPause.setImageResource(R.drawable.playmh3_pause);
                try {
                    imageCover.clearAnimation(); // Dừng hiệu ứng xoay
                } catch (Exception e) {
                    e.printStackTrace(); // Xử lý ngoại lệ
                }
            }
            mediaPlayer.release(); // Giải phóng tài nguyên
            mediaPlayer = null; // Đặt về null để tránh lỗi
        }
        super.onBackPressed(); // Gọi phương thức cha để thực hiện hành động quay lại
    }
}