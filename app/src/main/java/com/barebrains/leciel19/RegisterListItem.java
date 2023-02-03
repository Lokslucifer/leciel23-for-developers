package com.barebrains.leciel19;

public class RegisterListItem {
    String title,about,url;

    public RegisterListItem() {
    }

    public RegisterListItem(String title, String about , String url) {
        this.title = title;
        this.about = about;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getAbout() {
        return about;
    }

    public String getUrl() {
        return url;
    }
}
