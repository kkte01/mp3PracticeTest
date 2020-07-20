package com.example.a20200720mostpicture;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int[] idArray = {R.id.iv1,R.id.iv2,R.id.iv3,R.id.iv4,R.id.iv5,R.id.iv6,
            R.id.iv7,R.id.iv8,R.id.iv9};
    private ImageView[] imageViews = new ImageView[9];
    private Button btnResult;
    private int[] count = new int[9];
    private int[] imageFileId = {R.drawable.daengdaeng,R.drawable.nangnang,R.drawable.haedal,
            R.drawable.angrybird,R.drawable.namolbbaemi,R.drawable.pachiris,
            R.drawable.good,R.drawable.irin,R.drawable.jenie};
    private ResearchResult researchResult;
    private ArrayList<ResearchResult> arrayList = new ArrayList<ResearchResult>();
    private String[] name = {"댕댕이","냥냥이","해달이","김관우","나몰빼미","에몽가","갓슬기","아이린","제니"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIdFunction();

    }


    //멤버변수와 아이디를 매치 시키주고 이벤트를 처리하는 함수
    private void findViewByIdFunction() {
        for(int i = 0; i<idArray.length; i++){
            imageViews[i] = findViewById(idArray[i]);
            arrayList.add(new ResearchResult(0,imageFileId[i],name[i]));
            imageViews[i].setOnClickListener(this);
        }
        btnResult = findViewById(R.id.btnResult);
        btnResult.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId() != R.id.btnResult){

            for(int i =0; i<imageViews.length; i++){
                if(idArray[i] == view.getId()){
                    count[i] ++;
                    arrayList.get(i).setCount(count[i]);
                    Toast.makeText(getApplicationContext(),name[i]+": 총 "+arrayList.get(i).getCount()+" 표",Toast.LENGTH_SHORT).show();
                    break;20
                }
            }//end of for
        }else {
            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
            intent.putExtra("Result",arrayList);
            startActivityForResult(intent,101);

        }
    }//end of onClick

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 101){
            switch (resultCode){
                case 103 : ResearchResult researchResult = intent.getParcelableExtra("name");
                Toast.makeText(getApplicationContext(),"우승자 : "+researchResult.getName(),Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
