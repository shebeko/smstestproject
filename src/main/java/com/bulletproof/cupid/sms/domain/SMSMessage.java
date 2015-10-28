package com.bulletproof.cupid.sms.domain;

import java.util.Date;

/**
 * Created by shds on 17.10.2015.
 */
public class SMSMessage {
    private Integer id;
    private String number;
    private String text;
    private Date sentDate;
    private String sentStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public SMSMessage setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getText() {
        return text;
    }

    public SMSMessage setText(String text) {
        this.text = text;
        return this;
    }

    public String getSentDate() {
        return sentDate.toString();
    }

    public Date getSentDateTime() {
        return sentDate;
    }

    public SMSMessage setSentDate(Date sentDate) {
        this.sentDate = sentDate;
        return this;
    }

    public String getSentStatus() {
        return sentStatus;
    }

    public SMSMessage setSentStatus(String sentStatus) {
        this.sentStatus = sentStatus;
        return this;
    }
}
