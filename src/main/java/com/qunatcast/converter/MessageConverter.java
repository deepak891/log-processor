package com.qunatcast.converter;

import com.qunatcast.model.BackMessage;
import com.qunatcast.model.Message;

public interface MessageConverter<K, V, R extends BackMessage> {
       R convert(final Message<K, V> message);
}
