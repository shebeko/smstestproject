package com.bulletproof.cupid.sms.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import static com.bulletproof.cupid.sms.service.SmsService.*;

import java.util.Random;

/**
 * Created by shds on 19.10.2015.
 */
@Service
public class SmsSender {
    private final static Logger log = Logger.getLogger(SmsSender.class);
    private final Random random = new Random();

    public boolean sendSms(String phone, String message){
        boolean success = random.nextBoolean();
        String status = success ? SUCCESS : FAIL;
        log.info("message sent to " + phone + " with status " + status + ":" + System.getProperty("line.separator") + message);
        return success;
    }
}