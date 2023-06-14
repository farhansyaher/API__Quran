package com.example.api_quran.retrofit;

import com.example.api_quran.models.ChapterModel.Chapters;
import com.example.api_quran.models.VerseModel.Verses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("chapters?language=id")
    Call<Chapters> getSurah();

    @GET("quran/verses/indopak")
    Call<Verses> getAyat(@Query("chapter_number") int id);
}
