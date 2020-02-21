package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
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

        findViewById(R.id.btnSendVal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strVal = etText.getText().toString();
                int intVal = Integer.valueOf(etNumber.getText().toString());

                Log.d("EditText(str)", strVal);
                Log.d("EditText(int)", " " + intVal);

                Intent intent = new Intent(CallerActivity.this,
                                    CalleeActivity.class);
                intent.putExtra("data_string", strVal);
                intent.putExtra("data_int", intVal);

                startActivity(intent);

            }
        });

    }
}
