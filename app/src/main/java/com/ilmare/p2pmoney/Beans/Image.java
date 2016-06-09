package com.ilmare.p2pmoney.Beans;

import java.io.Serializable;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/9/2016 1:39 PM
 * 版本号： 1.0
 * 版权所有(C) 6/9/2016
 * 描述：
 * ===============================
 */
public class Image implements Serializable {
    private String ID;
    private String IMAPAURL;
    private String IMAURL;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIMAPAURL() {
        return IMAPAURL;
    }

    public void setIMAPAURL(String IMAPAURL) {
        this.IMAPAURL = IMAPAURL;
    }

    public String getIMAURL() {
        return IMAURL;
    }

    public void setIMAURL(String IMAURL) {
        this.IMAURL = IMAURL;
    }
}
