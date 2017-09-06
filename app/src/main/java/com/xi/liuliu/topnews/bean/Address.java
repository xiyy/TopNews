package com.xi.liuliu.topnews.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangxb171 on 2017/9/5.
 */

public class Address implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(number);
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    private Address(Parcel in) {
        name = in.readString();
        number = in.readString();
    }
}
