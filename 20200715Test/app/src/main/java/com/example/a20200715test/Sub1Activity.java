package com.example.a20200715test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Sub1Activity extends AppCompatActivity {
    private Button btnMoveFrag1;
    private Button btnMoveFrag2;
    private Button btnMoveFrag3;
    private CalendarView calendarView;
    private EditText edtContent;
    private Button btnSave;
    private String fileName;
    private MainActivity mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        //아이디를 멤버변수와 연결시키는 함수
        findViewByIdFunction();
        //이벤트 등록하기
        //캘린더 뷰 클릭스 이벤트
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //일기를 저장하거나 수정하는 함수
                readDiary(year,month,day);
            }
        });
        //버튼 클릭에 관한 이벤트 처리
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(view);
            }
        });


    }

    //일기를 저장하거나 수정하는 함수
    private void readDiary(int year, int month, int day) {
        fileName = year+""+(month+1)+""+day+".txt";
        //저장되어있는 파일을 읽어오기
        try {
            FileInputStream in = openFileInput(fileName);
            byte[]content = new byte[3000];
            in.read(content);
            //반납하기
            in.close();
            //읽었을 경우 여기를 실행
            edtContent.setText(new String(content));
            btnSave.setText("일기 수정 하기");
            //저장된 파일이 없을 경우 여기서 받는다.
        } catch (FileNotFoundException e) {
            edtContent.setText("");
            //오류시 로그에 남기기
            Log.d("Sub1Activity",e.getMessage());
            edtContent.setHint("작성한 내용이 없습니다. 내용을 입력해주세요");
            btnSave.setText("일기 저장 하기");
            //읽었으나 오류일 경우 여기서 받는다.
        } catch (IOException e1) {
            //오류시 로그에 남기기
            Log.d("Sub1Activity",e1.getMessage());
            edtContent.setHint("일기 불러오기 실패");
            btnSave.setText("일기 수정 하기");
        }
    }
    //버튼 클릭에 관한 핸들러 함수
    private void saveDiary(View view) {
        //파일저장하기
        String text = ((Button)view).getText().toString();
        try {
            FileOutputStream out = openFileOutput(fileName,MODE_PRIVATE);
            String context = edtContent.getText().toString();
            //문자열을 바이트로 변환
            out.write(context.getBytes());
            //반납하기
            out.close();
            switch (text){
                case "일기 수정 하기" :
                    Toast.makeText(getApplicationContext(),"일기 수정 완료",Toast.LENGTH_SHORT).show(); break;
                case "일기 저장 하기" :
                    Toast.makeText(getApplicationContext(),"일기 저장 완료",Toast.LENGTH_SHORT).show(); break;
            }
        } catch (IOException e) {
            //오류시 로그에 남기기
            Log.d("Sub1Activity",e.getMessage());
            Toast.makeText(getApplicationContext(),"~저장~",Toast.LENGTH_SHORT).show();
        }
    }

    //아이디를 멤버변수와 연결시키는 함수
    private void findViewByIdFunction() {
        btnMoveFrag1 = findViewById(R.id.btnMoveFrag1);
        btnMoveFrag2 = findViewById(R.id.btnMoveFrag2);
        btnMoveFrag3 = findViewById(R.id.btnMoveFrag3);
        calendarView = findViewById(R.id.calendarView);
        edtContent = findViewById(R.id.edtContent);
        btnSave = findViewById(R.id.btnSave);
    }

    //옵션메뉴를 메모리에 올려 객체화 시키기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //버튼 클릭에 대한 이벤트 처리
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.oMchageScreen1 :
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent); finish(); break;
            case R.id.oMchageScreen2 :
                Intent intent1 = new Intent(getApplicationContext(),Sub2Activity.class);
                startActivity(intent1); finish(); break;
        }
        return super.onOptionsItemSelected(item);
    }

}
