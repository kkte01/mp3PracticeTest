package com.example.chapter05test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edtNumber1;
    private EditText edtNumber2;
    private int[] btnIDArray = new int[]{R.id.btnAdd,R.id.btnSub,R.id.btnMul,R.id.btnDiv};
    private Button [] btnCalArray = new Button[4];
    private TextView tvResult;
    private Button[] btnArray = new Button[10];
    private int[] idArray = new int[]{R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.
            btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9};
    private static final int REQUSET_NUM = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ui객체를 컨트롤러 멤버변수와 연결해주는 함수
        findViewByidFunction();

        //이벤트 등록 및 처리
        for(int i  =0; i<btnArray.length; i++) {
            final int index;
            index = i;
            btnArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //임시객체안에 지역변수를 쓸 수 없다. 그래서 위에서 그 i값을 파이널로 선언해서 줘야한다.
                    //왜냐면 for문이 끝나면 지역변수가 사라지기 때문에 기억할 수 있게 선언해야한다.
                    //임시객체안에 지역변수를 넣을 경우에는 그 지역변수를 final 선언한후에 넣어줘야한다.
                    handleBtnNumberClickListener(index);
                }
            });
        }
            tvResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),Sub2Activity.class);
                    startActivity(intent);
                }
            });
            for(int i1  =0; i1<btnCalArray.length; i1++){
                btnCalArray[i1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        handleBtnCalClickListener(view);
                    }
                });
        }

    }
    //값을 받는 함수

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1010){
            switch (requestCode){
                case 10 : ArrayList<Calculate>arrayList = (ArrayList<Calculate>) data.getSerializableExtra("result");
                Calculate calculate = arrayList.get(0);
                    tvResult.setText(String.valueOf(calculate.getResult()));
            }
        }
    }

    //수식 계산 버튼에 관한 이벤트
    private void handleBtnCalClickListener(View view) {
        //view를 버튼으로 형변환 값을 가져오기 위해서
        Button calculate = (Button)view;
        String cal = calculate.getText().toString();
        int number1 = Integer.parseInt(edtNumber1.getText().toString());
        int number2 = Integer.parseInt(edtNumber2.getText().toString());

        Intent intent = new Intent(getApplicationContext(), Sub1Activity.class);
        Calculate calculate1 = new Calculate(number1,number2,cal,0);
        ArrayList<Calculate>calculateArrayList = new ArrayList<Calculate>();
        calculateArrayList.add(calculate1);
        intent.putExtra("list", calculateArrayList);
        startActivityForResult(intent,REQUSET_NUM);
    }


    // ui객체를 컨트롤러 멤버변수와 연결해주는 함수
    private void findViewByidFunction() {
        edtNumber1 = (EditText)findViewById(R.id.edtNumber1);
        edtNumber2 = (EditText)findViewById(R.id.edtNumber2);
        for(int i =0; i<btnArray.length; i++){
            btnArray[i] = (Button) findViewById(idArray[i]);
        }
        for(int i =0; i<btnIDArray.length; i++){
            btnCalArray[i] = (Button) findViewById(btnIDArray[i]);
        }
        tvResult = (TextView)findViewById(R.id.tvResult);
    }
    // 버튼에 대한 이벤트 핸들러 함수
    private void handleBtnNumberClickListener(int number) {
        if(edtNumber1.isFocused()){
            String number1 = edtNumber1.getText().toString()+number;
            edtNumber1.setText(number1);
        }else if(edtNumber2.isFocused()){
            String number2 = edtNumber2.getText().toString()+number;
            edtNumber2.setText(number2);
        }
    }
}

