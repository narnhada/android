package com.example.threadexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    private int sum = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // 단순한 UI Update의 경우는 기본 핸들러를 사용
//        handler = new Handler();

        // sendMessage로 전송된 메시지 처리를 위해서는
        // handleMessage를 다시 구현해 줘야 한다
        handler = new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                    textView.setText(String.format("what: %d, %s, total: %d", msg.what,
                            msg.obj, msg.arg1));
            }
        };
    }

    // UI Update를 위한 Thread >> Handler로 전송할 쓰레드
    public class UIUpdate implements Runnable {
        @Override
        public void run() {
            // 쓰레드의 실행 코드
            textView.setText("sum= " + sum);

        }
    }

    //개발자 쓰레드
    public class MyThread extends Thread {

        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                sum += i;
                // textView에 중간 결과를 표시
                handler.post(new UIUpdate());

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {

                }
            }
            handler.post(new UIUpdate());
        }
    }

    public void onBtnThreadClick(View v) {
        // 1부터 100까지 합산하면서
        // 중간 과정을 textView에 출력 (200ms 휴식)

        // 1. thread 없이 진행  //핸들러없어서 메인 스레드에 접근 못함
//        sum = 0;
//
//        for(int i=1; i<=100; i++){
//            sum += i;
//            textView.setText(String.format("합산중: %d", sum));
//
//            try {
//                Thread.sleep(200);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//
//        }

        // Thread를 이용하여 합산
        Thread thread = new MyThread();
        thread.start(); // 클래스 내부의 run 메서드가 수행된다
    }

    // Runnable 인터페이스를 이용한 쓰레드
    class RunnableThread implements Runnable {
        @Override
        public void run() {
            int total = 0;

            for (int i = 1; i <= 100; i++) {
                total += i;
                // Handler로 메시지를 전송
                Message msg = new Message();
                msg.what = 1;  // 어떤 메시지인가를 식별
                msg.obj = "합산중"; // 메시지로 전송할 Object
                msg.arg1 = total;
                msg.arg2 = 0;

                // 메시지를 핸들러로 전송
                handler.sendMessage(msg);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {

                }
            }
            // 완료 메시지를 전송
            Message msg = new Message();
            msg.what = 2;
            msg.obj = "합산 완료";
            msg.arg1 = total;
            msg.arg2 = 0;

            handler.sendMessage(msg);
        }
    }

    public void onBtnRunnableClick(View v){
        // 쓰레드를 실행
        Thread thread = new Thread(new RunnableThread());
        thread.start();
    }
}
