package com.example.a20200715test;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    private ImageView imgPic;
    private Button btnThrow;
    private RadioGroup rg1;
    private String name;
    private MainActivity mainActivity;

    @Override
    public void onAttach(Context context) {
        mainActivity = (MainActivity)getActivity();
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        mainActivity = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //메모리에 올려 객체화 시키기
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_1,container,false);
        //아이디 매치시키기
        btnThrow = root.findViewById(R.id.btnThrow);
        imgPic = root.findViewById(R.id.imgPic);
        rg1 = root.findViewById(R.id.rg1);
        //이벤트 걸기
        //라디오 버튼 클릭에 대한 이벤트
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                handleCheckedChangeListener(i);
            }
        });
        //이동하기 버튼 클릭에 대한 이벤트
        btnThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                mainActivity.fragment2.setArguments(bundle);
                Toast.makeText(getContext(),"전달완료",Toast.LENGTH_SHORT).show();
                mainActivity.changeScreen(2);
            }
        });

        return root;
    }
    //라디오 버튼 클릭에 대한 이벤트 핸들러 함수
    private void handleCheckedChangeListener(int i) {
        switch (i){
            case R.id.cha1 : imgPic.setImageResource(R.drawable.charizard);
                name = "charizard"; break;
            case R.id.chax : imgPic.setImageResource(R.drawable.charizardx);
                name = "charizardx";break;
            case R.id.chay : imgPic.setImageResource(R.drawable.charizardy);
                name = "charizardy";break;
        }
    }
}
