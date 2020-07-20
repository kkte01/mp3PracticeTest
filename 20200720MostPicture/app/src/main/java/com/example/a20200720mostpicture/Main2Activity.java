package com.example.a20200720mostpicture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private RatingBar[] ratingBars = new RatingBar[9];
    private int [] ratingId = {R.id.rb1,R.id.rb2,R.id.rb3,R.id.rb4,R.id.rb5,
            R.id.rb6,R.id.rb7,R.id.rb8,R.id.rb9};
    private Button btnReturn;
    private TextView tvName;
    private ImageView ivWin;
    private float top = 0.0f;
    private ResearchResult researchResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewByIdFunction();
        Intent intent = getIntent();

        final ArrayList<ResearchResult>arrayList = intent.getParcelableArrayListExtra("Result");

        for(int i =0;i<arrayList.size(); i++){
            if(arrayList.get(i).getCount() > 6)
                arrayList.get(i).setCount(5);

            ratingBars[i].setRating(arrayList.get(i).getCount());

            if(ratingBars[i].getRating()>top){
                top = ratingBars[i].getRating();
            }
            if(arrayList.get(i).getCount() == top){
                tvName.setText(arrayList.get(i).getName());
                ivWin.setImageResource(arrayList.get(i).getImageFileid());
            }
        }
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                ResearchResult researchResult = new ResearchResult(tvName.getText().toString());
                intent1.putExtra("name",researchResult);
                setResult(103,intent1);
                finish();
            }
        });
    }
    //아이디를 찾는 함수
    private void findViewByIdFunction() {
        for(int i =0; i<ratingId.length; i++){
            ratingBars[i] = findViewById(ratingId[i]);
        }
        btnReturn = findViewById(R.id.btnReturn);
        tvName = findViewById(R.id.tvName);
        ivWin = findViewById(R.id.ivWin);
    }
}
