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

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private RecyclerView recyclerView;

    private MainAdapter mainAdapter;

    private List<ChaptersItem> results = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        setUpRecyclerView();
        getDataFromApi();

    }

    private void setUpRecyclerView() {
        mainAdapter = new MainAdapter(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void setUpView() {

        recyclerView = findViewById(R.id.recyclerView);
    }

    private void getDataFromApi (){
        ApiService.endPoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                if (response.isSuccessful()){
                    List<ChaptersItem> result = response.body().getChapters();
                    Log.d(TAG, result.toString());
                    mainAdapter.setData(result);
                }

            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }
}