package com.xi.liuliu.topnews.event;

/**
 * Created by liuliu on 2017/6/28.
 */

public class LoginResultEvent {
    private boolean isLoginSuccessful;

    public LoginResultEvent(boolean isLoginSuccessful) {
        this.isLoginSuccessful = isLoginSuccessful;
    }

    public boolean getLoginResult() {
        return isLoginSuccessful;
    }
}
