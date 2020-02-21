package com.example.customadapterexample;

public class MovieVo {
    private int image; // 이미지 리소스의 id
    private String title; // 영화 제목
    private String year; // 개봉 년도

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "MovieVo{" +
                "image=" + image +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
