package com.example.api_quran.models.AudioModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Audios{

	@SerializedName("audio_files")
	private List<AudioFilesItem> audioFiles;

	public List<AudioFilesItem> getAudioFiles(){
		return audioFiles;
	}
}