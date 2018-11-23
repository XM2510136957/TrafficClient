package com.mad.trafficclient.Entity;

public class MyMsgEntity {
    private int no;
    private String type;
    private int value;
    private int nowValue;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNowValue() {
        return nowValue;
    }

    public void setNowValue(int nowValue) {
        this.nowValue = nowValue;
    }

    public MyMsgEntity(int no, String type, int value, int nowValue) {
        this.no = no;
        this.type = type;
        this.value = value;
        this.nowValue = nowValue;
    }
}
