package com.example.api_quran;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api_quran.models.AudioModel.AudioFilesItem;
import com.example.api_quran.models.AudioModel.Audios;
import com.example.api_quran.models.VerseModel.Verses;
import com.example.api_quran.models.VerseModel.VersesItem;
import com.example.api_quran.retrofit.ApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class DetailSurahActivity extends AppCompatActivity {

    private Button btnPlay, btnPause, btnStop;
    private MediaPlayer mediaPlayer;
    private RecyclerView recyclerView;
    private AdapterVerses adapterAyats;
    private List<VersesItem> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        int id = getIntent().getIntExtra("id", 1);

        setUpView();
        setUpRecyclerView();
//        setUpAudioView();
        System.out.println(id);
        getDataFromApi(id);
    }

//    private void setUpAudioView() {
//        adapterAudios = new AdapterAudios(results);
//        mediaPlayer.setAdapter(adapterAudios);
//    }

    private void setUpRecyclerView() {
        adapterAyats = new AdapterVerses(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterAyats);
    }

    private void setUpView() {
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        recyclerView = findViewById(R.id.recyclerViewAyat);
    }

    private void getDataFromApi(int id){
        ApiService.endPoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if(response.isSuccessful()){
                    List<VersesItem> result = response.body().getVerses();
                    Log.d("Ayat", result.toString());
                    adapterAyats.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
                Log.d("Ayat", t.toString());
            }
        });

        ApiService.endPoint().getAudio(id).enqueue(new Callback<Audios>() {

            @Override
            public void onResponse(Call<Audios> call, Response<Audios> response) {
//                if(response.isSuccessful()) {
//                    List<AudioFilesItem> result = response.body().getAudioFiles();
//                    Log.d("Audio", result.toString());
////                    String url = "https://api.quran.com/api/v4/chapter_recitations/7/";
//                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    try {
//                        mediaPlayer.setDataSource(result.toString());
//                        mediaPlayer.prepare();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
                btnPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mediaPlayer == null) {
                            mediaPlayer = MediaPlayer.create(DetailSurahActivity.this, R.id.btnPlay);
                        }
                        mediaPlayer.start();
                    }
                });
                btnPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mediaPlayer == null) {
                            mediaPlayer.pause();
                        }
                    }
                });
                btnStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mediaPlayer == null) {
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<Audios> call, Throwable t) {
                Log.d("Audio", t.toString());
            }
        });
    }
}