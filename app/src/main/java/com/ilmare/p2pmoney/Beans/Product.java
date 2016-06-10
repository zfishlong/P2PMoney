package com.ilmare.p2pmoney.Beans;

import java.io.Serializable;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/10/2016 7:18 PM
 * 版本号： 1.0
 * 版权所有(C) 6/10/2016
 * 描述：
 * ===============================
 */

public class Product implements Serializable {
   private  String id;
   private  String name;
   private  String money;
   private  String yearLv;
   private  String suodingDays;
   private  String minTouMoney;
   private  String memberNum;
   private  String progress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getYearLv() {
        return yearLv;
    }

    public void setYearLv(String yearLv) {
        this.yearLv = yearLv;
    }

    public String getSuodingDays() {
        return suodingDays;
    }

    public void setSuodingDays(String suodingDays) {
        this.suodingDays = suodingDays;
    }

    public String getMinTouMoney() {
        return minTouMoney;
    }

    public void setMinTouMoney(String minTouMoney) {
        this.minTouMoney = minTouMoney;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
