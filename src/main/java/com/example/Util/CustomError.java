package com.example.util;

/**
 * Created by EgorPC on 09.02.2017.
 */
public class CustomError {

    private  String errorMessage;

    public CustomError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
