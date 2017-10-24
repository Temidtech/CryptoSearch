package com.swiftsynq.charis.cryptosearch.Events;

/**
 * Created by TEMIDJOY on 8/15/2017.
 */

public class ExRateErrorEvent {
    private int errorCode;
    private String errorMsg;

    public ExRateErrorEvent(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
