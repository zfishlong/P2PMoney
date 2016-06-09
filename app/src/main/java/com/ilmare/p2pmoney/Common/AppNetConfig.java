package com.ilmare.p2pmoney.Common;


public interface AppNetConfig {

    String HOST = "192.168.191.4";

    String BASEURL = "http://" + HOST + ":8080/P2PInvest/";

    String LOGIN = BASEURL + "login";

    String PRODUCT = BASEURL + "product";

    String INDEX = BASEURL + "index";


}
