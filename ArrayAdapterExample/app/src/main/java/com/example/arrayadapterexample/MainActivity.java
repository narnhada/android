package com.example.arrayadapterexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView list; // ListView: AdapterView
    private String[] data; // Adapter에서 DataSource로 사용할 문자열 배열

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);  // AdapterView
        //리소스에서 데이터 불러오기
        data = getResources().getStringArray(R.array.android_list);  //DataSource

//        // 데이터 소스와 뷰 렌더링을 위한 Adapter 필요
//        // 방법 1. 안드로이드의 기본 레이아웃을 사용하는 경우
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
//                android.R.layout.simple_list_item_1,    //출력할 내용이 1개인 경우
//                data);
        // 방법 2. 커스텀 레이아웃을 사용하는 경우
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.list_item_main,  // 커스텀 레이아웃
                R.id.textView,            // 데이터를 렌더링할 위젯의 ID
                data);

        // ListView에 Adapter 세팅
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, // 이벤트 발생시킨 AdapterView
                                    View view,             //
                                    int position,          // 전체 데이터 소스 중의 몇번째 인덱스 인지
                                    long id) {
                Toast.makeText(MainActivity.this,
                        data[position], Toast.LENGTH_LONG).show();

            }
        });

    }
}
