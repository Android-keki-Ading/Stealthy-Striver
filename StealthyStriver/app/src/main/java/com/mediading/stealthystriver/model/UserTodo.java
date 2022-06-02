package com.mediading.stealthystriver.model;

public class UserTodo {
    private String title;
    private String desc;
    private String datetime ;
    private Boolean done;

    public UserTodo(String title, String desc, String datetime) {
        this.title = title;
        this.desc = desc;
        this.datetime = datetime;
        this.done = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
