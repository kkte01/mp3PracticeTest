package com.example.a20200715test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyImageView extends View {
    //이미지를 가져올 경로를 담을 변수 선언
    private String imagePath;
    //자식은 부모의 생성자를 책임져야 하기때문에 생성자 오버로딩 해야한다.
    //1개의 매개변수가 있는 생성자는 클래스내에서 객체를 만들어서 사용하겠다.
    public MyImageView(Context context) {
        super(context);
    }
    //2개의 매개변수2개가 있는 UI는 xml에서 Drag & Drop 으로 위치를 배치시킨다.
    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.imagePath = null;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //보여줘야할 이미지파일을 불러서 화면에다가 넣어줘야한다.
        if(imagePath != null){
            //1. imagePath안에 있는 그림을 해석해서 비트맵에 그려준다.
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            //2. 비트맵을 넣을 캔버스 크기를 설정한다.
            //이렇게 하면 딱 맞춰서 나온다.
            canvas.scale((float)canvas.getWidth()/bitmap.getWidth(),(float)canvas.getHeight()/bitmap.getHeight()
            ,0,0);
            //3. 넣어준다.
            canvas.drawBitmap(bitmap,0,0,null);
            bitmap.recycle();
        }

    }
}
