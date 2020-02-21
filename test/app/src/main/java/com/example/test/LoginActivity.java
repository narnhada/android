package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tvId = findViewById(R.id.tvId);
        TextView tvPwd = findViewById(R.id.tvPwd);

        Intent intent = getIntent();

        String strLog = (String)intent.getExtras().get("data_id");
        int intPwd = intent.getIntExtra("data_pwd",0);

        tvId.setText(strLog);
        tvPwd.setText(String.valueOf(intPwd));

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("로그인");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("로그인 되었습니다.");
        builder.setCancelable(false);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("item", which + "번호");
            }
        }).show();
    }
}
