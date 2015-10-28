package com.bulletproof.cupid.sms.controller;

import com.bulletproof.cupid.sms.domain.Data;
import com.bulletproof.cupid.sms.domain.ResponseContainer;
import com.bulletproof.cupid.sms.domain.SMSMessage;
import com.bulletproof.cupid.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shds on 17.10.2015.
 */
@Controller
@RequestMapping("/")
public class SmsController {

    @Autowired
    private SmsService service;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome() {
        return "index";
    }

    @RequestMapping(value = "/sms/", method = RequestMethod.GET)
    public @ResponseBody ResponseContainer fetch() {
        return service.getAll();
    }

    @RequestMapping(value = "/sms/submit/", method = RequestMethod.POST)
    public @ResponseBody SMSMessage submit(@RequestBody Data data) {
        return service.create(data);
    }
}