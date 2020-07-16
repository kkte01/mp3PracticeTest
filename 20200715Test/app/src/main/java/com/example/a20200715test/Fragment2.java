package com.example.a20200715test;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private MainActivity mainActivity;
    private ImageView imageView;
    private EditText edtInfor;
    private Button btnC;
    private TextView tvType;
    private TextView tvName;
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
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_2,container,false);
        //아이디 매치시키는 함수
        findViewByIdFunction(root);

        if(mainActivity.fragment2.getArguments()!=null){
            String name = mainActivity.fragment2.getArguments().getString("name");
            switch (name){
                case "charizard": imageView.setImageResource(R.drawable.charizard); tvName.setText("리 자 몽"); tvType.setText("불/비행"); break;
                case "charizardx": imageView.setImageResource(R.drawable.charizardx); tvName.setText("메 가 리 자 몽 X"); tvType.setText("불/드래곤"); break;
                case "charizardy": imageView.setImageResource(R.drawable.charizardy); tvName.setText("메 가 리 자 몽 Y"); tvType.setText("불/비행");break;
            }
            mainActivity.fragment2.setArguments(null);
        }else{
            Toast.makeText(getContext(),"받아온 값이 없다!",Toast.LENGTH_SHORT).show();
        }
        //이벤트 등록
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.changeScreen(3);
            }
        });

        return root;
    }
    //아이디 매치시키는 함수
    private void findViewByIdFunction(ViewGroup root) {
        imageView = root.findViewById(R.id.imageView);
        edtInfor = root.findViewById(R.id.edtInfor);
        btnC = root.findViewById(R.id.btnC);
        tvType = root.findViewById(R.id.tvType);
        tvName = root.findViewById(R.id.tvName);
    }
}
