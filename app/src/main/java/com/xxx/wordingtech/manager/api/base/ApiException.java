package com.xxx.wordingtech.manager.api.base;

import java.io.IOException;

public class ApiException extends IOException {
    int statusCode;
    String errorMsg;

    public ApiException(int statusCode, String errorMsg) {
        this.statusCode = statusCode;
        this.errorMsg = errorMsg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
