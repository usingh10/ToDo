package com.example.utkarshsingh.todo;

/**
 * Created by Utkarsh Singh on 09-11-2016.
 */
public class Row {
    private String title;
    private String detail;
    public Row(String title, String detail)
    {
        this.title=title;
        this.detail = detail;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}