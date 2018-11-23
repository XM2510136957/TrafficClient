package com.mad.trafficclient.Entity;

public class InfoEntity {
    private int NO;
    private String cardId;
    private int money;
    private String operator;
    private String time;
    private int money2;

    public int getMoney2() {
        return money2;
    }

    public void setMoney2(int money2) {
        this.money2 = money2;
    }

    public InfoEntity(){

    }

    public int getNO() {
        return NO;
    }

    public void setNO(int NO) {
        this.NO = NO;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public InfoEntity(int NO, String cardId, int money, String operator, String time) {
        this.NO = NO;
        this.cardId = cardId;
        this.money = money;
        this.operator = operator;
        this.time = time;
    }

    public InfoEntity(int money2) {
        this.money2 = money2;
    }
}
