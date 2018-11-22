package com.mad.trafficclient.bean;

public class Billing {
    private int carId;
    private int recharge;
    private String operator;
    private String time;

    public Billing(int carId, int recharge, String operator, String time) {
        this.carId = carId;
        this.recharge = recharge;
        this.operator = operator;
        this.time = time;
    }

    public int getCarId() {
        return carId;
    }

    public int getRecharge() {
        return recharge;
    }

    public String getOperator() {
        return operator;
    }

    public String getTime() {
        return time;
    }
}
