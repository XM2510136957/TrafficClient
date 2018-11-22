package com.mad.trafficclient.bean;

public class CZJL {
    private String userName;//充值人
    private String carNumber;//车牌号
    private int charge; //充值金额
    private int balance;//余额
    private String[] time;//充值时间

    public CZJL(String userName, String carNumber, int charge, int balance, String[] time) {
        this.userName = userName;
        this.carNumber = carNumber;
        this.charge = charge;
        this.balance = balance;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public int getCharge() {
        return charge;
    }

    public int getBalance() {
        return balance;
    }

    public String[] getTime() {
        return time;
    }
}
