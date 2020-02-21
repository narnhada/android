package com.example.movielistapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list;
    private static final String SERVER = "http://192.168.21.1:3000";
    List<Movie> data;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);

        //  네트워크 요청 (Volley)
        //  RequestQueue를 생성
        queue = Volley.newRequestQueue(this);
        //  JSON 요청 생성
        String moviesUrl = SERVER + "/movies";
        Log.d("[URL]", moviesUrl);

        JsonObjectRequest req =
                new JsonObjectRequest(
                    Request.Method.GET, //  요청 방식
                        moviesUrl,  //  요청 URL
                        null,
                        new Response.Listener<JSONObject>() {
                            //  성공시의 리스너
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("[Response]", response.toString());
                                //  json object에서
                                //  movies JSONArray -> GSON 이용, 데이터 소스로 변환
                                //  Adapter 생성 -> list에 연결
                                try {
                                    JSONArray movies = response.getJSONArray("movies");
                                    Log.d("[movies]", movies.toString());

                                    Gson gson = new Gson();
                                    Movie[] arr = gson.fromJson(movies.toString(),
                                            Movie[].class); //  JSON의 배열 -> 실제 VO 객체의 배열

                                    //  배열 -> 리스트로 변환
                                    data = new ArrayList<>(Arrays.asList(arr));
                                    //  어댑터 생성
                                    MovieAdapter adapter =
                                            new MovieAdapter(MainActivity.this,
                                                    R.layout.list_item_movie,   // 레이아웃
                                                    data);  //  데이터 소스
                                    list.setAdapter(adapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            //  실패시의 리스너
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );

        queue.add(req);
    }

    //  ViewHolder의 구현
    class MovieHolder {
        private ImageView imageView;
        private TextView tvTitle;
        private TextView tvDescription;

        public MovieHolder(View root) {
            imageView = root.findViewById(R.id.imageView);
            tvTitle = root.findViewById(R.id.tvTitle);
            tvDescription = root.findViewById(R.id.tvDescription);
        }
    }

    //  Custom Adapter
    class MovieAdapter extends ArrayAdapter<Movie> {
        int resource; //    레이아웃의 id
        List<Movie> data;

        public MovieAdapter(@NonNull Context context,
                            int resource,
                            List<Movie> data) {
            super(context, resource);
            this.data = data;
            this.resource = resource;
        }

        @Override
        public int getCount() {
            //  데이터 소스의 아이템의 갯수 반환
            return data.size();
        }

        @NonNull
        @Override
        public View getView(int position,
                            @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            MovieHolder holder;

            if (convertView == null) {
                //  새로 레이아웃 객체를 생성
                LayoutInflater inflater =
                        (LayoutInflater)getContext()
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(resource, null);
                holder = new MovieHolder(convertView);
                convertView.setTag(holder);
            }

            holder = (MovieHolder)convertView.getTag();

            final ImageView imageView = holder.imageView;
            TextView tvTitle = holder.tvTitle;
            TextView tvDescription = holder.tvDescription;

            //  필요한 영화 정보를 획득
            Movie movie = data.get(position);

            //  이미지를 다운로드 받아서 imageView에 반영
            String imageUrl = SERVER + "/images/" + movie.getImage();
//            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            //  이미지 요청을 생성
            ImageRequest req = new ImageRequest(
                    imageUrl, //    이미지의 URL
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            imageView.setImageBitmap(response);
                        }
                    },
                    0,  //  최대 가로 사이즈
                    0,  //  최대 세로 사이즈
                    ImageView.ScaleType.CENTER_INSIDE,  //  이미지 뷰에 표시하는 방식
                    Bitmap.Config.RGB_565,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            );
            queue.add(req); //  요청 전송

            tvTitle.setText(movie.getTitle());
            tvDescription.setText(movie.getDirector() + ", " + movie.getYear());

            return convertView;
//            return super.getView(position, convertView, parent);
        }
    }
}
