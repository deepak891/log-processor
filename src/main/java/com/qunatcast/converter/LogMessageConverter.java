package com.qunatcast.converter;

import com.qunatcast.model.BackMessage;
import com.qunatcast.model.CookieMessage;
import com.qunatcast.model.LogMessage;
import com.qunatcast.model.Message;
import com.qunatcast.utils.DateUtil;

import java.time.LocalDateTime;

public class LogMessageConverter implements MessageConverter<LocalDateTime, LogMessage, BackMessage> {
    
    @Override
    public BackMessage convert(Message<LocalDateTime, LogMessage> message) {
        final LogMessage payload = message.getPayload();
        BackMessage cookieMessage = new CookieMessage(payload.getCookie(),
                DateUtil.toDate(payload.getDateTime()));
        return cookieMessage;
    }
}
