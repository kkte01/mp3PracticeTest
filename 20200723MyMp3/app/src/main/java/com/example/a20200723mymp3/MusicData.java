package com.example.a20200723mymp3;

import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class MusicData {
    private Bitmap musicPicture;
    private String musicTitle;
    private String artist;
    private String duration;


    /*public MusicData(Bitmap bitmap, String musicTitle, String artistName, double duration) {
        this.musicTitle = musicTitle;
    }*/

    public MusicData(Bitmap musicPicture, String musicTitle, String artist, String duration) {
        this.musicPicture = musicPicture;
        this.musicTitle = musicTitle;
        this.artist = artist;
        this.duration = duration;
    }

    public Bitmap getMusicPicture() {
        return musicPicture;
    }

    public void setMusicPicture(Bitmap musicPicture) {
        this.musicPicture = musicPicture;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
