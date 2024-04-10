package com.example.mychatapp;
public class GroupMessageModel {
    private String messageId;
    private String senderId;
    private String messageText;
    private String time;
    private String groupName;
    private String groupIds;
    private String groupUserNames;
    private String groupCreator;
    private String groupId;

    public GroupMessageModel() {

    }

    public GroupMessageModel(String groupId, String senderId, String messageText, String time, String groupName, String groupUserNames, String groupIds, String groupCreator) {
        this.senderId = senderId;
        this.messageText = messageText;
        this.time = time;
        this.groupName = groupName;
        this.groupUserNames = groupUserNames;
        this.groupIds = groupIds;
        this.groupCreator = groupCreator;
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupCreator() {
        return groupCreator;
    }

    public void setGroupCreator(String groupCreator) {
        this.groupCreator = groupCreator;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getGroupUserNames() {
        return groupUserNames;
    }

    public void setGroupUserNames(String groupUserNames) {
        this.groupUserNames = groupUserNames;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
