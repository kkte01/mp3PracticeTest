package com.example.a20200715test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {
    private MainActivity mainActivity;
    private Button btnReturn;

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
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_3,container,false);
        //아이디 매치시키는 함수
        findViewByIdFunction(root);
        //이벤트 등록
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
    //아이디 매치시키는 함수
    private void findViewByIdFunction(ViewGroup root) {
        btnReturn = root.findViewById(R.id.btnReturn);
    }
}
