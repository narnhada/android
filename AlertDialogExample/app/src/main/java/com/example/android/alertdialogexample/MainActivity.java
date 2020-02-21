package com.example.android.alertdialogexample;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int indexSingleChoice = 0; // 단일 선택 대화상자의 선택값을 저장해 두기 위한 변수
    private  boolean[] indexMultiChoice = {
            false, false, false, false, false, false, false, false
            // 배열의 크기는 항목의 갯수와 일치해야 한다 >> false에서 true가 되는 값들 중복 선택하는 법
    }; // 중복 선택 상자가면됨

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 단순 메시지 대화상자 출력
    public void dialogMessage(View v) {         // xml파일을 보면 onClick에 'dialogMessage'로 정해줌 ,id값 아님
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setIcon(R.mipmap.ic_launcher);  // 아이콘 알림 >> 으로 생긴 icon 생김
        builder.setMessage("Hello, Android");
        // 설정을 가지고 대화 상자 보여주기
        builder.show();
    }

    // 닫기 버튼이 있는 대화상자
    public void dialogCloseButton(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Hello, Android");
        builder.setCancelable(true);                                  // 다른곳을 눌러도 창이 닫힘
        builder.setNegativeButton("닫기", null).show(); // 닫기 버튼 누르면 닫아짐
    }

    // Ok,Cancel, Neutal 세 개의 버튼이 있는 다이얼로그
    public void dialogOKCancelButton(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // this는 누가 사용하는지 알려줌 지금은 MainAtivity
        builder.setTitle("알림")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("Hello, Android")
                .setCancelable(false) // 임의 취소 불가 옵션
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("item", which + "번호");
                        Toast.makeText(MainActivity.this, "YES 버튼 클릭",
                                Toast.LENGTH_LONG).show();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("item", which + "번호");
                        Toast.makeText(MainActivity.this, "Cancel 버튼 클릭",
                                Toast.LENGTH_LONG).show();
                    }
                })

                .setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("item", which + "번호");
                        Toast.makeText(MainActivity.this, "No 버튼 클릭",
                                Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

    // List 대화 상자
    public void dialogList(View v) {
        //R.array.lists  >> res/values/arrys.xml
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요")
                .setItems(R.array.lists, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { // 배열에 선택된 항목의 인덱스
                        // array 리소스로 String 배열 만들기
                        String[] androidList = getResources().getStringArray(R.array.lists);

                        Toast.makeText(MainActivity.this,
                                androidList[which] + "를 선택",
                                Toast.LENGTH_LONG).show();
                    }
                }).show();
    }

    // Progress Dialog
    public void dialogProgress(View v) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("처리중");
        progress.setIcon(R.mipmap.ic_launcher);
        progress.setMessage("잠시만 기다려 주십시오");
        progress.show();
    }

    // 단일 항목 선택 대화상자
    public void dialogSingleChoice(View v) {
        new AlertDialog.Builder(this)
                .setTitle("단일 항목 선택")
                .setIcon(R.mipmap.ic_launcher)
                .setSingleChoiceItems(R.array.lists, // 목록에 표시할 문자열 배열(리소스)
                        indexSingleChoice, // 선택된 항목의 인덱스 >> 0이면 다시 켤때마다 0번째에 있음
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] androidList = getResources().getStringArray(R.array.lists);
                                Toast.makeText(MainActivity.this,
                                        androidList[which] + "를 선택하셨습니다.",
                                        Toast.LENGTH_LONG).show();

                                indexSingleChoice = which;
                            }
                        })
                .setPositiveButton("Ok", null).show();
    }

    // 중복 선택 대화상자
    public void dialogMultipleChoice(View v) {
        new AlertDialog.Builder(this)
                .setTitle("중복 선택 대화 상자")
                .setIcon(R.mipmap.ic_launcher)
                .setMultiChoiceItems(R.array.lists,// 표시할 항목의 문자열 배열(리소스)
                        indexMultiChoice, // 각 항목의 선택 여부를 저장하는 boolean 배열
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Log.d("[Item 선택]", which + "항목 선택?" + isChecked);
                                indexMultiChoice[which] = isChecked;
                            }
                        }
                ).show();
    }

    // 사용자 정의 다이얼로그
    public void dialogCustomLayout(View v) {
        // dialog_custom.xml >> 레이아웃 객체로 만들어 내야 한다
        LayoutInflater inflater = getLayoutInflater(); // xml에 정의된 resouce를 view객체로 반환
        // inflater는 xml 레이아웃을 실제 객체로 변환해주는 기능을 수행
        final View rootView = inflater.inflate(R.layout.dialog_custom, null);  // rootView를 아래 클래스에서 사용하기 위해 fianl로 만듬

        new AlertDialog.Builder(this)
                .setTitle("Custom Dialog")
                .setIcon(R.mipmap.ic_launcher)
                .setView(rootView) // 다이얼로그 내부에서 사용할 레이아웃 객체
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() { // 임시로 클래스를 만들어주었다 >> MainActivity와 별개
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("[CustomDialog]", "사용자 정의 다이얼로그");
                        // dialog_custom 레이아웃의 password의 값을 얻어와야 한다
                        EditText password = rootView.findViewById(R.id.password);
                        Toast.makeText(MainActivity.this,
                                "사용자 암호: " + password.getText().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }).show();
    }
}
