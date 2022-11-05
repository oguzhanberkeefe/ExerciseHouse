package com.example.exercisehouse.adapters;

/**
 * Created by LiuShen on 2018/9/3 0003.
 */
public class MainBean {


    private String title;
    private String Category;
    private int Thumbnail;

    public MainBean() {
    }

    public MainBean(String title , String category, int thumbnail) {
        this.title = title;
        Category = category;
        Thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }


}
