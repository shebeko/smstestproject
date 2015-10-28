package com.bulletproof.cupid.sms.repository.rowmapper;

import com.bulletproof.cupid.sms.domain.SMSMessage;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shds on 17.10.2015.
 */
@Component
public class SMSMessageRowMapper implements RowMapper<SMSMessage> {
    public SMSMessage mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        SMSMessage m = new SMSMessage();
        m.setId(rs.getInt("sms_id"));
        m.setNumber(rs.getString("phone_number"));
        m.setText(rs.getString("message"));
        m.setSentDate(rs.getTimestamp("send_date"));
        m.setSentStatus(rs.getString("send_status"));
        return m;
    }
}