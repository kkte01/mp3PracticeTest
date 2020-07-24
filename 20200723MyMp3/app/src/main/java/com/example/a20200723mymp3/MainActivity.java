package com.example.a20200723mymp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private ImageButton ibPrevious;
    private ImageButton ibStart;
    private ImageButton ibPause;
    private ImageButton ibStop;
    private ImageButton ibNext;
    private TextView tvMusicName;
    private ProgressBar progressBar;
    private String path;
    private ArrayList<MusicData> musicDataArrayList = new ArrayList<MusicData>();
    //메타데이터를 이용하기위해 선언
    private MediaMetadataRetriever media = new MediaMetadataRetriever();
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String selectMusic;
    private String previousMusic;
    private String nextMusic;
    private int flag = 0;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //아이디 매치 및 이벤트 등록 함수
        findViewByIdfunction();

        //외부 장지 권한 얻는 함수
        requestPermissionsFunction();

        //파일을 가져올 절대경로를 설정한다.
        path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Music/";
        //그 경로에 있는 파일중 mp3파일만 선택한다.
        loadMP3fileSDcard();

        //내가 만든 어댑터 설정하기
        MusicAdapter musicAdapter = new MusicAdapter(getApplicationContext());
        musicAdapter.setMusicDataArrayList(musicDataArrayList);
        listView.setAdapter(musicAdapter);
        //listView 클릭시 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                selectMusic = musicDataArrayList.get(i).getMusicTitle() + ".mp3";
                /*if(i==0){
                    index = musicDataArrayList.size();
                    previousMusic = musicDataArrayList.get(index-1).getMusicTitle()+".mp3";
                }else{
                    previousMusic = musicDataArrayList.get(index-1).getMusicTitle()+".mp3";

                }*/

               /* if(i == musicDataArrayList.size()-1){
                    i= -1;
                    nextMusic = musicDataArrayList.get(i+1).getMusicTitle()+".mp3";

                }else if(i != musicDataArrayList.size()-1){
                    nextMusic = musicDataArrayList.get(i+1).getMusicTitle()+".mp3";
                }*/
            }
        });



    }
    //그 경로에 있는 파일중 mp3파일만 선택한다.
    private void loadMP3fileSDcard() {
        //안에 있는 파일 다 가져오기
        File[] mp3files = new File(path).listFiles();
        //그중 mp3파일 분류하기
        for(File file : mp3files){
            String fileName = file.getName().trim();
            if(fileName.length() >=4 ){
                String extendName = fileName.substring(fileName.length()-3);
                if(extendName.equals("mp3")){
                    media.setDataSource(path+fileName);
                    //mp3파일에 들어있는 메타 데이터 이용하기
                    byte[] data = media.getEmbeddedPicture();
                    Bitmap bitmap = null;
                    if(data !=null){
                        bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                    }
                    String title = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                    String artistName= media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                    String duration = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                    MusicData musicData = new MusicData(bitmap,title,artistName,duration);
                    musicDataArrayList.add(musicData);
                }
            }
        }
    }

    //외부 장지 권한 얻는 함수
    private void requestPermissionsFunction() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
    }

    //아이디 매치 및 이벤트 등록 함수
    private void findViewByIdfunction() {
        listView = findViewById(R.id.listView);
        ibPrevious = findViewById(R.id.ibPrevious);
        ibStart = findViewById(R.id.ibStart);
        ibPause = findViewById(R.id.ibPause);
        ibStop = findViewById(R.id.ibStop);
        ibNext = findViewById(R.id.ibNext);
        tvMusicName = findViewById(R.id.tvMusicName);
        progressBar = findViewById(R.id.progressBar);

        //이벤트 등록
        ibPrevious.setOnClickListener(this);
        ibStart.setOnClickListener(this);
        ibPause.setOnClickListener(this);
        ibStop.setOnClickListener(this);
        ibNext.setOnClickListener(this);
    }
    //이벤트 처리 함수
    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.ibPrevious :
                 try {
                     if(index == 0){
                         index = musicDataArrayList.size();
                     }
                     index--;
                     previousMusic = musicDataArrayList.get(index).getMusicTitle()+".mp3";

                     mediaPlayer.stop();
                     mediaPlayer.reset();
                     mediaPlayer.setDataSource(path+previousMusic);
                     mediaPlayer.prepare();
                     mediaPlayer.start();
                 } catch (IOException e) {
                     Log.d("ubPrevious",e.getMessage());
                 }
                 break;
             case R.id.ibStart :
                 try {
                     mediaPlayer.setDataSource(path+selectMusic);
                     //재생을 준비하는 시간을 준다.
                     mediaPlayer.prepare();
                     //재생하기
                     mediaPlayer.start();
                     //버튼 상태 변화 함수
                     btnStatusChange(false,true,true);
                     tvMusicName.setText(selectMusic);
                 } catch (IOException e) {
                     Log.d("btnStart",e.getMessage());
                 }
                 break;
             case R.id.ibPause :
                 mediaPlayer.pause();
                 if(flag == 0){
                     ibPause.setImageResource(R.drawable.play);
                     ibStart.setVisibility(View.INVISIBLE);
                     btnStatusChange(false,true,true);
                     flag =1;
                 }else if(flag == 1){
                     mediaPlayer.start();
                     ibPause.setImageResource(R.drawable.pause);
                     ibStart.setVisibility(View.VISIBLE);
                     btnStatusChange(false,true,true);
                     flag = 0;
                 }
                 break;
             case R.id.ibStop :
                 mediaPlayer.stop();
                 mediaPlayer.reset();
                 btnStatusChange(true,false,false);
                 ibStart.setVisibility(View.VISIBLE);
                 ibPause.setImageResource(R.drawable.pause);
                 flag = 0;
                 break;
             case R.id.ibNext :
                 try {
                     mediaPlayer.stop();
                     mediaPlayer.reset();
                     if(index == musicDataArrayList.size()-1){
                         index= -1;
                     }
                     index++;
                     nextMusic = musicDataArrayList.get(index).getMusicTitle()+".mp3";
                     mediaPlayer.setDataSource(path+nextMusic);
                     mediaPlayer.prepare();
                     mediaPlayer.start();
                 } catch (IOException e) {
                     Log.d("ibNext",e.getMessage());
                 }
                 break;
             default: break;
         }
    }
    //버튼 상태 변화 함수
    private void btnStatusChange(boolean b, boolean b1, boolean b2) {
        ibStart.setClickable(b);
        ibPause.setClickable(b1);
        ibStop.setClickable(b2);
    }
}
