package com.xi.liuliu.topnews.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuliu on 2017/6/15.
 */

public class News {
    private String reason;
    @SerializedName("error_code")
    private int errorCode;
    private Result result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
