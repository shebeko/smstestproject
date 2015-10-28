package com.bulletproof.cupid.sms.domain;

/**
 * Created by shds on 18.10.2015.
 */
public class Data {
    private String phoneNumber;
    private String text;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}