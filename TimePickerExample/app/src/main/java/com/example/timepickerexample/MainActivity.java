package com.example.timepickerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TimePicker tp;
    private TextView timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위젯 연결
        timeText = findViewById(R.id.timeText);
        tp = findViewById(R.id.timePicker);

        // 이벤트 리스너 연결
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            // 시간이 변경될 때 OnTimeChanged 이벤트 발생
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // timeText에 시간을 표시
                timeText.setText(String.format("%d:%d",hourOfDay, minute));
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // timePicker로 부터 시간과 분 정보를 받아오기
                int hour, minute;
                // 현재 상황: minimum SDK가 메서드를 지원하지 않음
                // SDK버전 확인하여 적합한 메서드를 실행
                if (Build.VERSION.SDK_INT >= 23) {
                    // SDK 버전이 23이상인 경우
                    hour = tp.getHour();
                    minute = tp.getMinute();
                } else {
                    // SDK 버전이 23 미만인 경우
                    hour = tp.getCurrentHour();
                    minute = tp.getCurrentMinute();
                }
                    Toast.makeText(MainActivity.this,
                            String.format("%d:%d", hour, minute), Toast.LENGTH_LONG).show();

            }
        });
    }
}
