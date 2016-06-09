package com.ilmare.p2pmoney.Beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/9/2016 1:39 PM
 * 版本号： 1.0
 * 版权所有(C) 6/9/2016
 * 描述：
 * ===============================
 */

public class Index implements Serializable {
    private ArrayList<Image> imageArr;
    private ProInfo proInfo;

    public ArrayList<Image> getImageArr() {
        return imageArr;
    }

    public void setImageArr(ArrayList<Image> imageArr) {
        this.imageArr = imageArr;
    }

    public ProInfo getProInfo() {
        return proInfo;
    }

    public void setProInfo(ProInfo proInfo) {
        this.proInfo = proInfo;
    }
}
