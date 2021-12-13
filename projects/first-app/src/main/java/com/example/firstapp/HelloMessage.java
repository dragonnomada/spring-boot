package com.example.firstapp;

import java.util.Date;

public class HelloMessage {
    
    private final String message;
    private final Date createAt;
    private final int status;

    HelloMessage(String message, Date createAt, int status) {
        this.message = message;
        this.createAt = createAt;
        this.status = status;
    }
    
    public String getMessage() {
        return this.message;
    }


    public Date getCreateAt() {
        return this.createAt;
    }


    public int getStatus() {
        return this.status;
    }

}
