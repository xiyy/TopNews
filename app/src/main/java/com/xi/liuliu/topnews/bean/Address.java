package com.xi.liuliu.topnews.bean;

/**
 * Created by zhangxb171 on 2017/9/5.
 */

public class Address {
    private String name;
    private String number;

    public Address(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
