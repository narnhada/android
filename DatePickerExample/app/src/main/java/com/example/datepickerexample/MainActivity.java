package com.example.datepickerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {
    private DatePicker dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DatePicker 초기화
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);

        Log.d("[현재시간]", year + "년" + (month+1) + "월" + date + "일"); //자바 month는 0부터 시작 해서 1 더해줌

        dp = findViewById(R.id.datePicker);
        dp.init(year, month, date, new  DatePicker.OnDateChangedListener(){

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 날짜가 변경되었을 때마다 발생
                // textView에 이 값을 출력
                ((TextView)findViewById(R.id.timeText))
                        .setText(year + "-" + (monthOfYear+1) + "-"+dayOfMonth);

            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dp로 부터 날짜를 받아와서 Toast로 띄우기
                int year = dp.getYear();
                int month = dp.getMonth() + 1;
                int date = dp.getDayOfMonth();

                Toast.makeText(MainActivity.this,
                            String.format("%d-%d-%d",year,month,date),
                            Toast.LENGTH_SHORT).show();

            }
        });
    }
}
