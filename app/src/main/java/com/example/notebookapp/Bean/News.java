package com.example.notebookapp.Bean;

public class News {
    int id;
    String webTitle;
    String webUrl;
    String date;

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

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", webTitle='" + webTitle + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
