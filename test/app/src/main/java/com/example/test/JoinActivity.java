package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        final EditText etLogin = findViewById(R.id.etLogin);
        final EditText etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                builder.setTitle("로그인");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("로그인 중입니다.");
                builder.setCancelable(false);
                builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("item", which + "번호");

                        String strLog = etLogin.getText().toString();
                        int intPwd = Integer.valueOf(etPassword.getText().toString());

                        Log.d("EditText(str)", strLog);
                        Log.d("EditText(int)", " " + intPwd);

                        Intent intent = new Intent(JoinActivity.this,
                                LoginActivity.class);


                        intent.putExtra("data_id", strLog);
                        intent.putExtra("data_pwd", intPwd);
                        startActivity(intent);
                    }
                })
                        .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("item", which + "번호");

                            }
                        }).show();
            }
        });
    }
}
