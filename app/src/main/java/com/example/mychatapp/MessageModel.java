package com.example.mychatapp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MessageModel {
    private String messageId;
    private String senderId;
    private String message;
    private long messsageNumber = 0;
    private static long counter = 0;
    public MessageModel() {
    }

    public MessageModel(String messageId, String senderId, String message) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.message = message;
        this.messsageNumber = counter;
        counter += 1;
    }

    public long getMesssageNumber() {
        return messsageNumber;
    }

    public void setMesssageNumber(long messsageNumber) {
        this.messsageNumber = messsageNumber;
    }

    public static long getCounter() {
        return counter;
    }

    public static void setCounter(long counter) {
        MessageModel.counter = counter;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
