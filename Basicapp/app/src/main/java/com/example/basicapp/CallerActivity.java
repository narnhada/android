package com.example.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CallerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caller);

        final EditText etText = findViewById(R.id.etString);
        final EditText etNumber = findViewById(R.id.etNumber);

        findViewById(R.id.btnSendVal).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // EditText에서 입력된 값을 뽑아 내기
                String strVal = etText.getText().toString();
                int intVal = Integer.valueOf(etNumber.getText().toString());

                Log.d("EditText(str)", strVal);
                Log.d("EditText(int)", " " + intVal);

                // 인텐트에 putExtra에 추가, CalleeActibity를 호출
                Intent intent = new Intent(CallerActivity.this,
                        CalleeActivity.class);
                // 부가 데이터를 putExtra로 추가
                intent.putExtra("data_string", strVal);
                intent.putExtra("data_int", intVal);

                startActivity(intent);

            }
        });


    }
}
