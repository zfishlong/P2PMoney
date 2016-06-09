package com.ilmare.p2pmoney.Beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/9/2016 1:40 PM
 * 版本号： 1.0
 * 版权所有(C) 6/9/2016
 * 描述：
 * ===============================
 */

public class ProInfo implements Serializable {
    private String id;
    private String memberNum;
    private String minTouMoney;
    private String money;
    private String name;
    private String progress;
    private String suodingDays;
    private String yearLv;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getMinTouMoney() {
        return minTouMoney;
    }

    public void setMinTouMoney(String minTouMoney) {
        this.minTouMoney = minTouMoney;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getSuodingDays() {
        return suodingDays;
    }

    public void setSuodingDays(String suodingDays) {
        this.suodingDays = suodingDays;
    }

    public String getYearLv() {
        return yearLv;
    }

    public void setYearLv(String yearLv) {
        this.yearLv = yearLv;
    }
}
