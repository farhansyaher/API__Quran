package com.example.api_quran.models.VerseModel;

import com.google.gson.annotations.SerializedName;

public class VersesItem{

	@SerializedName("verse_key")
	private String verseKey;

	@SerializedName("text_indopak")
	private String textIndopak;

	@SerializedName("id")
	private int id;

	public String getVerseKey(){
		return verseKey;
	}

	public String getTextIndopak(){
		return textIndopak;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"VersesItem{" + 
			"verse_key = '" + verseKey + '\'' + 
			",text_indopak = '" + textIndopak + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}