package com.example.customadapterexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);

        //DataSource
        List<MovieVo> data = new ArrayList<>();
        MovieVo movie = new MovieVo();
        movie.setImage(R.drawable.toystory);
        movie.setTitle( "토이스토리");
        movie.setYear("1995");

        data.add(movie);

        movie = new MovieVo();
        movie.setImage(R.drawable.toystory_2);
        movie.setTitle( "토이스토리2");
        movie.setYear("1999");

        data.add(movie);

        movie = new MovieVo();
        movie.setImage(R.drawable.toystory_3);
        movie.setTitle( "토이스토리3");
        movie.setYear("2010");

        data.add(movie);

        movie = new MovieVo();
        movie.setImage(R.drawable.toystory_4);
        movie.setTitle( "토이스토리4");
        movie.setYear("2018");

        data.add(movie);

        // Adapter 객체 생성
        MovieAdater adapter = new MovieAdater(this,
                R.layout.list_item_movie,data);

        // list에 adapter 세팅
        list.setAdapter(adapter);
    }
    // ViewHolder 생성: 성능 향상을 위해 레이아웃 객체를 미리 만들어 두고 필요할 때 받아와서 데이터만 교체하는 패턴
    // :view 객체의 재활용
    class MovieHoder{
        public ImageView imageView;
        public TextView tvTitle;
        public TextView tvYear;

        //생성자
        public MovieHoder(View rootView){
            // rootView는 객체 생성시 외부에서 layout을 inflate 해서 넘겨주는 레이아웃 객체
            this.imageView = rootView.findViewById(R.id.imageView);
            this.tvTitle = rootView.findViewById(R.id.tvTitle);
            this.tvYear = rootView.findViewById(R.id.tvYear);
        }
    }
    // Custom Adapter
    class MovieAdater extends ArrayAdapter<MovieVo>{
        Context context;            // 컨텍스트
        int resource;               // 레이아웃의 id
        List<MovieVo> movieList;    // DataSource

        public MovieAdater(@NonNull Context context,
                           int resource,
                           @NonNull List<MovieVo> objects) {
            super(context, resource);
            this.context = context;
            this.resource = resource;
            this.movieList = objects;
        }
        // DataSource 아이템의 갯수
        @Override
        public int getCount() {
            return movieList.size();
        }

        @NonNull
        @Override
        public View getView(int position,   // 인덱스값 넘겨줌
                            @Nullable View convertView,
                            @NonNull ViewGroup parent) {
//            return super.getView(position, convertView, parent);
            if (convertView == null){
                // ViewHolder가 없음 >> 새로 만들어 줘야함
                LayoutInflater inflater =
                        (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // 레이아웃을 객체화 >> 성능향상을 위해 사용
                convertView = inflater.inflate(resource, null);
                MovieHoder holder = new MovieHoder(convertView);
                convertView.setTag(holder);

            }
            MovieHoder holder = (MovieHoder)convertView.getTag(); // 이미 만들어진 뷰 객체를 다시 재사용

            ImageView imageView = holder.imageView;
            TextView tvTitle = holder.tvTitle;
            TextView tvYear = holder.tvYear;

            MovieVo vo = movieList.get(position);

            imageView.setImageDrawable(
                    ResourcesCompat.getDrawable(
                            context.getResources(),
                            vo.getImage(),
                            null));
            tvTitle.setText(vo.getTitle());
            tvYear.setText(vo.getYear());

            return convertView;
        }
    }
}
