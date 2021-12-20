package com.qunatcast.model;

/**
 * This is wrapper message, it is used to as a packet of message
 * when message added and removed from queue
 * @param <K>
 * @param <V>
 */
public class Message<K, V> {
    private K key;
    private V payload;
    private MessageType messageType;

    public Message(K key, V payload, MessageType messageType) {
        this.key = key;
        this.payload = payload;
        this.messageType = messageType;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getPayload() {
        return payload;
    }

    public void setPayload(V payload) {
        this.payload = payload;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
