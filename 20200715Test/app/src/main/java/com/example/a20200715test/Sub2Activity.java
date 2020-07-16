package com.example.a20200715test;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;

public class Sub2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPrev;
    private Button btnNext;
    private TextView tvSelected;
    private MyImageView myImageView;
    private String path = "";
    public File[] fileArray = new File[100];
    //선택된 인덱스를 저장할 변수 선언
    private int index = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        //아이디 매치 시키는 함수
        findViewByIdFunction();
        //sd카드를 이용할 권한을 얻기
        ActivityCompat.requestPermissions(Sub2Activity.this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        //사진을 가져올 경로를 지정한다.
        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        //경로에서 가져온 사진들을 파일 배열에 담는다.(여러장이기때문에)
        fileArray =new File(path+"/Image").listFiles();
        //첫번째 화면을 보여준다.
        myImageView.setImagePath(fileArray[index].toString());
        tvSelected.setText((index+1)+"/"+fileArray.length);
        //이벤트 등록해버리기
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        tvSelected.setOnClickListener(this);
        tvSelected.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Sub1Activity.class);
                startActivity(intent);
                finish();
                return false;
            }
        });
    }

    //아이디 매치시키는 함수
    private void findViewByIdFunction() {
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        tvSelected = findViewById(R.id.tvSelected);
        myImageView = findViewById(R.id.myImageView);
    }
    //이벤트를 처리하는 함수
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnPrev : if(index == 0) index = fileArray.length;
                //복합대입연산자
                index -=1;
                myImageView.setImagePath(fileArray[index].toString());
                tvSelected.setText((index+1)+"/"+fileArray.length);
                //고의적으로 무효화영역처리를 해서 onDraw 함수를 부른다.
                myImageView.invalidate();
                break;
            case R.id.btnNext : if(index == (fileArray.length-1)) index = -1;
                index += 1;
                myImageView.setImagePath(fileArray[index].toString());
                tvSelected.setText((index+1)+"/"+fileArray.length);
                myImageView.invalidate();
                break;
            case R.id.tvSelected :
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
