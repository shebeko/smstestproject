package com.bulletproof.cupid.sms.repository;

import com.bulletproof.cupid.sms.domain.SMSMessage;
import com.bulletproof.cupid.sms.repository.rowmapper.SMSMessageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by shds on 17.10.2015.
 */
@Repository
public class JDBCRepository {

    private static final String FIND_ALL_SQL = "SELECT * FROM sms_records";
    private static final String CREATE_SQL = "INSERT INTO sms_records (phone_number, message, send_date, send_status)" +
            "VALUES (:phoneNumber, :message, :sendDate, :sendStatus)";

    @Autowired
    NamedParameterJdbcTemplate template;
    @Autowired
    SMSMessageRowMapper rowMapper;

    public List<SMSMessage> getMessages() {
        return template.query(FIND_ALL_SQL, new HashMap<String, Object>(), rowMapper);
    }

    public SMSMessage store(SMSMessage msg) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("phoneNumber", msg.getNumber())
                .addValue("message", msg.getText())
                .addValue("sendDate", msg.getSentDateTime())
                .addValue("sendStatus", msg.getSentStatus());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(CREATE_SQL, params, keyHolder);
        msg.setId(keyHolder.getKey().intValue());
        return msg;
    }
}