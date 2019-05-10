package com.example.notebookapp.Bean;

import java.io.Serializable;

public class News implements Serializable {
    private int id;
    private String webTitle;
    private String webUrl;
    private String date;
    private boolean isSelected;


    public News(){}

    public News(int id, String webTitle, String webUrl, String date) {
        this.id = id;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }




    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", webTitle='" + webTitle + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", date='" + date + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
