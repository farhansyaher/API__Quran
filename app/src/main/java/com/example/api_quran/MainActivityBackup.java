package com.example.api_quran;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api_quran.models.ChapterModel.Chapters;
import com.example.api_quran.models.ChapterModel.ChaptersItem;
import com.example.api_quran.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityBackup extends AppCompatActivity {
    private final String TAG = "MainActivity";

    RecyclerView recyclerView;
    AdapterSurahs adapterSurahs;
    ArrayList<SurahModel> objSurah = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        objSurah.add(new SurahModel(1, "LatinSurah", "ArabSurah",new TranslatedName("ArtiSurah")));
        objSurah.add(new SurahModel(2, "LatinSurah", "ArabSurah",new TranslatedName("ArtiSurah")));
        objSurah.add(new SurahModel(3, "LatinSurah", "ArabSurah",new TranslatedName("ArtiSurah")));

        adapterSurahs = new AdapterSurahs(objSurah);
        recyclerView.setAdapter(adapterSurahs);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivityBackup.this));
        getDataFromApi();
    }

    private void getDataFromApi (){
        ApiService.endPoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                if (response.isSuccessful()){
                    List<ChaptersItem> result = response.body().getChapters();
                    Log.d(TAG, result.toString());
                }
            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }
}