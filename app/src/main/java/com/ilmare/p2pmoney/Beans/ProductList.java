package com.ilmare.p2pmoney.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/10/2016 7:18 PM
 * 版本号： 1.0
 * 版权所有(C) 6/10/2016
 * 描述：
 * ===============================
 */

public class ProductList implements Serializable {
    List<Product> productList=new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
