package com.example.a20200723mymp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter {
    //화면을 받을 변수
    private Context context;
    private ArrayList<MusicData>musicDataArrayList;

    public MusicAdapter(Context context) {
        this.context = context;
    }

    //보여줄 개수
    @Override
    public int getCount() {
        return musicDataArrayList.size();
    }
    //몇번째를 보여줄 건지
    @Override
    public Object getItem(int i) {
        return musicDataArrayList.get(i);
    }
    //몇번째로 보여줄건지
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //내가 만든 화면 객체화 시키기
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            view = layoutInflater.inflate(R.layout.music_main,null);
        }
        //아이디 매치시키기
        ImageView imgAlbum = view.findViewById(R.id.imgAlbum);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvArtist=view.findViewById(R.id.tvArtist);
        TextView tvlength=view.findViewById(R.id.tvlength);
        //객체만들기
        MusicData musicData = musicDataArrayList.get(i);
        //값 설정해주기
        imgAlbum.setImageBitmap(musicData.getMusicPicture());
        tvTitle.setText(musicData.getMusicTitle());
        tvArtist.setText(musicData.getArtist());
        tvlength.setText(musicData.getDuration());

        return view;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<MusicData> getMusicDataArrayList() {
        return musicDataArrayList;
    }

    public void setMusicDataArrayList(ArrayList<MusicData> musicDataArrayList) {
        this.musicDataArrayList = musicDataArrayList;
    }
}
