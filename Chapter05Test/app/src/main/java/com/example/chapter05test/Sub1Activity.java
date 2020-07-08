package com.example.chapter05test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Sub1Activity extends AppCompatActivity {

    private int[] tvIdArray = new int[]{R.id.tvNumber1,R.id.tvNumber2,R.id.tvCalculate,R.id.tvResult};
    private TextView[] tvArray = new TextView[4];
    private Button btnReturn;
    private int result;
    private ArrayList<Calculate>arrayList = new ArrayList<Calculate>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        findViewByIdFunction();
        //앞에서 intent 받기
        Intent intent = getIntent();
        arrayList = (ArrayList<Calculate>)intent.getSerializableExtra("list");
        Calculate calculate = arrayList.get(0);
        int number1 = calculate.getNumber1();
        int number2 = calculate.getNumber2();
        String cal = calculate.getCalculate();

        tvArray[0].setText(String.valueOf(calculate.getNumber1()));
        tvArray[1].setText(String.valueOf(calculate.getNumber2()));
        tvArray[2].setText(calculate.getCalculate());
        //연산자에 따른 결과값 계산
        switch (cal){
            case "더하기" : tvArray[3].setText(String.valueOf(number1+number2));
                            result = number1+number2;
                            break;
            case "빼기" : tvArray[3].setText(String.valueOf(number1-number2));
                result = number1-number2;
                break;
            case "곱하기" : tvArray[3].setText(String.valueOf(number1*number2));
                result = number1*number2;
                break;
            case "나누기" : tvArray[3].setText(String.valueOf(number1/number2));
                result = number1/number2;
                break;
        }
        // 결과값돌려주기 버튼에 대한 이벤트 등록
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnReturnClickListener(view);
            }
        });
    }
    // 결과값돌려주기 버튼에 대한 핸들러 함수 처리
    private void handleBtnReturnClickListener(View view) {
        Intent intent = new Intent();
        Calculate calculate = arrayList.get(0);
        calculate.setResult(result);
        arrayList.remove(0);
        arrayList.add(calculate);
        intent.putExtra("result",arrayList);
        setResult(1010,intent);
        finish();
    }

    //ui객체 id와 멤버변수를 연결해주는 함수
    private void findViewByIdFunction() {
        for(int i =0; i<tvIdArray.length; i++){
            tvArray[i] = (TextView)findViewById(tvIdArray[i]);
        }
        btnReturn = (Button) findViewById(R.id.btnReturn);
    }
}
