package com.mad.trafficclient.bean;

public class Suggest {
    private String title;
    private String time;
    private int status;
    private String context;
    private String huifutime;

    public Suggest() {
    }

    public Suggest(String title, String time, int status, String context, String huifutime) {
        this.title = title;
        this.time = time;
        this.status = status;
        this.context = context;
        this.huifutime = huifutime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getHuifutime() {
        return huifutime;
    }

    public void setHuifutime(String huifutime) {
        this.huifutime = huifutime;
    }
}
