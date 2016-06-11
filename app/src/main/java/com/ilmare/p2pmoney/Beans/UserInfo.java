package com.ilmare.p2pmoney.Beans;

import java.io.Serializable;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/10/2016 9:16 PM
 * 版本号： 1.0
 * 版权所有(C) 6/10/2016
 * 描述：
 * ===============================
 */

public class UserInfo implements Serializable {
    private String UF_ACC;
    private String UF_AVATAR_URL;
    private String UF_IS_CERT;
    private String UF_PHONE;

    public String getUF_AVATAR_URL() {
        return UF_AVATAR_URL;
    }

    public void setUF_AVATAR_URL(String UF_AVATAR_URL) {
        this.UF_AVATAR_URL = UF_AVATAR_URL;
    }

    public String getUF_IS_CERT() {
        return UF_IS_CERT;
    }

    public void setUF_IS_CERT(String UF_IS_CERT) {
        this.UF_IS_CERT = UF_IS_CERT;
    }

    public String getUF_PHONE() {
        return UF_PHONE;
    }

    public void setUF_PHONE(String UF_PHONE) {
        this.UF_PHONE = UF_PHONE;
    }

    public String getUF_ACC() {

        return UF_ACC;
    }

    public void setUF_ACC(String UF_ACC) {
        this.UF_ACC = UF_ACC;
    }
}
