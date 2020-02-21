package com.example.basicapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{ // 내부의 모든 위젯의 OnClickListener를 Activity가 통합관리

    public static final int REQUEST_CODE_SELECT = 1; // 상수로 해놔야 유지보수 용이 하다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // btnToast id를 가진 위젯을 찾아서 객체에 할당
        View btnToast = findViewById(R.id.btnToast);
        // btnToast가 클릭되면 Toast 띄어주는 EventListener를 등록
        btnToast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 전달된 v는 해당 이벤트를 발생시킨 View(위젯)
                Log.d("Click Event","btnToast 버튼이 Click되었음");
                Toast.makeText(MainActivity.this, // 해당 토스트를 생성한 인스턴스
                        "버튼이 클릭되었습니다.",    // 토스트에 표시할 메시지
                        Toast.LENGTH_LONG                 // 지속시간
                ).show();
            }                       // 원래 this로 작성이 가능한데 'new View.OnClickListener()'
        });                         // 객체를 가리키므로 MainActivity를 명시 해서 사용하기
                                    // 위해 MainActivity.this로 사용

        // btnNewActivity >> NewActivity를 실행
        View btnNewActivity = findViewById(R.id.btnNewActivity);
        btnNewActivity.setOnClickListener(this); // Activity의 setOnClickListener에서 처리

        // btnSendVal >> CallerActivity를 실행
        View btnSendVal = findViewById(R.id.btnSendVal);
        btnSendVal.setOnClickListener(this);    // onClick을 아래에서 사용한다.
                                                // 새로운 객체를 생성하지 않고 자기자신을 사용하므로
                                                // this사용이 가능하다

        // btnStartForResult >> startActivityForResult로 결과 값 받아오기
        View btnStartForResult = findViewById(R.id.btnStartForResult);
        btnStartForResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // 인자로 넘어온 v 객체(이벤트 발생 시킨 위젯)를 식별, 그에 맞는 처리를 해 줘야 함
        int senderId = v.getId();

        switch (senderId){
            case R.id.btnNewActivity:
                Log.d("[click]", "btnNewActivity가 Click 되었음");
                // NewActivity를 실행하는 코드

                // 새 액티비티 실행을 위해서는 Intent
                // 특정 액티비티를 직접 지정한 인텐트 >> 명시적 인텐트
                Intent newIntent = new Intent(this,NewActivity.class);
                startActivity(newIntent);
                break;

            case R.id.btnSendVal:
                Log.d("[Click]", "btnSendVal이 클릭 되었음");
                // CallerActivity를 실행
                Intent callerIntent = new Intent(this, CallerActivity.class);
                startActivity(callerIntent);
                break;

            case R.id.btnStartForResult:
                Log.d("[Click]", "btnStartForResult가 클릭됨");
                //startActivityForResult로 SelectActivity를 호출

                Intent startForResultIntent =
                        new Intent(MainActivity.this, SelectActivity.class);
                startActivityForResult(startForResultIntent, REQUEST_CODE_SELECT);
                                    // startActivity가 호출될때 여기서만 사용하는게 아니기 때문에
                                    // result값을 다르게 하여 원하는곳에 result값을 주기 위해 사용
                break;
        }
    }

    //startActivityForResult로 호출한 액티비티가 종료되었을 때 메시지가 넘어온다
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_SELECT && resultCode == RESULT_OK){
            //1번 요청이 성공 상태로 응답
            String result = data.getStringExtra("result");
            Toast.makeText(MainActivity.this,result, Toast.LENGTH_LONG).show();
        }
    }
}
