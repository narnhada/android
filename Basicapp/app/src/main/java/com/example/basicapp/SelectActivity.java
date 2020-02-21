package com.example.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectActivity extends AppCompatActivity
        implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        findViewById(R.id.btnScissor).setOnClickListener(this);
        findViewById(R.id.btnRock).setOnClickListener(this);
        findViewById(R.id.btnPaper).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String returnMessage = "";
        switch(v.getId()){
            case R.id.btnScissor:
                returnMessage = "가위";
                break;
            case R.id.btnRock:
                returnMessage = "바위";
                break;
            case R.id.btnPaper:
                returnMessage = "보";
                break;

        }
        Intent intent = getIntent();
        intent.putExtra("result", returnMessage);
        // 결과코드를 작성
        setResult(RESULT_OK, intent);

        finish(); // >> 자신을 호출했던 Activity에 Coallback을 돌려줌

    }
}
