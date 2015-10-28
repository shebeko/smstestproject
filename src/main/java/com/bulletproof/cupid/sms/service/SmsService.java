package com.bulletproof.cupid.sms.service;

import com.bulletproof.cupid.sms.domain.Data;
import com.bulletproof.cupid.sms.domain.ResponseContainer;
import com.bulletproof.cupid.sms.domain.SMSMessage;
import com.bulletproof.cupid.sms.repository.JDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

/**
 * Created by shds on 17.10.2015.
 */
@Service
public class SmsService {
    static final String SUCCESS = "SUCCESS";
    static final String FAIL = "FAIL";

    @Autowired
    private JDBCRepository repository;
    @Autowired
    private SmsSender sender;

    public ResponseContainer getAll() {
        ResponseContainer response = new ResponseContainer();
        List<SMSMessage> messagesList = repository.getMessages();
        SMSMessage[] messagesArray = new SMSMessage[messagesList.size()];
        response.setData(messagesList.toArray(messagesArray));
        return response;
    }

    public SMSMessage create(Data data) {
        boolean result = sender.sendSms(data.getPhoneNumber(), data.getText());
        SMSMessage message = new SMSMessage();
        message.setNumber(data.getPhoneNumber()).setText(data.getText()).setSentDate(new Date());
        message.setSentStatus(result ? SUCCESS : FAIL);
        repository.store(message);
        return message;
    }
}