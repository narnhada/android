package com.example.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CalleeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collee);

        TextView tvString = findViewById(R.id.tvString);
        TextView tvInt = findViewById(R.id.tvInt);

        // 전달 받은 인텐트를 얻어 오기
        Intent intent = getIntent();

        String strVal = (String)intent.getExtras().get("data_string");
        int intVal = intent.getIntExtra("data_int", 0);

        tvString.setText(strVal);
        tvInt.setText(String.valueOf(intVal));
    }
}
