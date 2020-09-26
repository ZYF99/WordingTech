package com.xxx.wordingtech.model.register;

public class RegisterResultModel {
    String token;
    String uid;

    public RegisterResultModel(String token, String uid) {
        this.token = token;
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
