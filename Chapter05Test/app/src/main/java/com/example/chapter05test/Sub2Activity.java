package com.example.chapter05test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Sub2Activity extends AppCompatActivity {
    private int[] imgIdArray = new int[]{R.id.img1,R.id.img2,R.id.img3};
    private ImageView[] imgArray = new ImageView[3];
    private int[] idArray = new int[]{R.id.btnScreen1,R.id.btnScreen2,R.id.btnScreen3};
    private Button[] btnArray = new Button[3];
    private int[] linearidArray = new int[]{R.id.linear1,R.id.linear2,R.id.linear3};
    private LinearLayout[] linearArray = new LinearLayout[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        findViewByIdFunction();
        for(int i2 =0; i2<btnArray.length; i2++){
            final int index2;
            index2 = i2;
            btnArray[i2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleBtnClickListener(index2);
                }
            });
        }

        for(int i2 =0; i2<imgArray.length; i2++){
            imgArray[i2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

    }
    // 누른 버튼에 관한 이벤트 처리 핸들러 함수
    private void handleBtnClickListener(int index2) {
        switch (index2){
            case 0 :   linearArray[index2].setVisibility(View.VISIBLE);
                        linearArray[index2+1].setVisibility(View.INVISIBLE);
                        linearArray[index2+2].setVisibility(View.INVISIBLE);
                        break;
            case 1 : linearArray[index2].setVisibility(View.VISIBLE);
                    linearArray[index2-1].setVisibility(View.INVISIBLE);
                    linearArray[index2+1].setVisibility(View.INVISIBLE);
            break;
            case 2 : linearArray[index2].setVisibility(View.VISIBLE);
                linearArray[index2-1].setVisibility(View.INVISIBLE);
                linearArray[index2-2].setVisibility(View.INVISIBLE);
            break;
        }
    }

    private void findViewByIdFunction() {
        for(int i2 = 0; i2<idArray.length; i2++){
            btnArray[i2] = (Button)findViewById(idArray[i2]);
        }
        for(int i3 = 0; i3<linearidArray.length; i3++){
            linearArray[i3] = (LinearLayout)findViewById(linearidArray[i3]);
        }
        for(int i3 = 0; i3<imgIdArray.length; i3++){
            imgArray[i3] = (ImageView) findViewById(imgIdArray[i3]);
        }
    }
}
