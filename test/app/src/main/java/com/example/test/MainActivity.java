package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View btnToast = findViewById(R.id.btnToast);
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Click Event","btnToast Click됨");
                Toast.makeText(MainActivity.this,
                        "버튼이 클릭되었습니다.",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        View btnNewActivity = findViewById(R.id.btnNewActivity);
        btnNewActivity.setOnClickListener(this);

        View btnSendVal = findViewById(R.id.btnSendVal);
        btnSendVal.setOnClickListener(this);

        View btnJoin = findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int senderId = v.getId();

        switch (senderId){
            case R.id.btnNewActivity:

                Log.d("[click]", "btnNewAcktivity가 Click되었음");

                Intent newIntent = new Intent(this,NewActivity.class);
                startActivity(newIntent);
                break;

            case R.id.btnSendVal:

                Log.d("[click]", "btnSendVal가 Click되었음");

                Intent callerIntent = new Intent(this, CallerActivity.class);
                startActivity(callerIntent);
                break;

            case R.id.btnJoin:
                Log.d("[click]", "btnJoin가 Click되었음");

                Intent joinintent = new Intent(this, JoinActivity.class);
                startActivity(joinintent);

                break;
        }
    }

//    public void LoginButton(View v) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("로그인");
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setMessage("로그인 중입니다.");
//        builder.setCancelable(true);
//        builder.setNegativeButton("닫기", null).show();
//    }

}
