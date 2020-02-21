package com.example.networkimageviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String SERVER = "http://192.168.21.1:3000";
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    //  AsyncTask 작성
    private class DownloadImageAsync extends AsyncTask<String, Void, Bitmap> {
        //  String: 이미지 URL String을 전달하기 위해
        //  Void : Progress -> onPregressUpdate에서 사용한 데이터 매개변수
        //  Bitmap: doInBackground의 result(결과 값) -> onPostExcute의 매개변수로 활용
        ImageView imageView;
        public DownloadImageAsync(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bitmap = null;

            try {
                InputStream is = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(is);
            } catch (MalformedURLException e) {

            } catch (IOException e) {

            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
            //  변환된 비트맵을 ImageView에 세팅
            imageView.setImageBitmap(bitmap);
        }
    }

    public void onBtnAsyncTaskClick(View v) {
        //  AsyncTask로 Network에 접속
        //  서버상의 /images/landscape.jpg를 다운로드 하여
        //  ImageView에 표시
        new DownloadImageAsync(imageView)
                .execute(SERVER + "/images/landscape.jpg");
        Log.d("[URL]", SERVER + "/images/landscape.jpg");
    }
}
