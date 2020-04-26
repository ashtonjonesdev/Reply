package dev.ashtonjones.reply.datamodels;

import java.util.ArrayList;

public class User {


    public User() {
    }

    public User(String name, String uID) {
        this.name = name;
        this.uID = uID;
    }

    public User(String name, String uID, ArrayList<MessageCard> personalMessages, ArrayList<MessageCard> socialMessages, ArrayList<MessageCard> businessMessages, ArrayList<MessageCard> plus1Messages, ArrayList<MessageCard> plus2Messages) {
        this.name = name;
        this.uID = uID;
        this.personalMessages = personalMessages;
        this.socialMessages = socialMessages;
        this.businessMessages = businessMessages;
        this.plus1Messages = plus1Messages;
        this.plus2Messages = plus2Messages;
    }

    public User(String name, String uID, ArrayList<MessageCard> personalMessages, ArrayList<MessageCard> socialMessages, ArrayList<MessageCard> businessMessages, ArrayList<MessageCard> plus1Messages, ArrayList<MessageCard> plus2Messages, MessageCard replyLaterMessage) {
        this.name = name;
        this.uID = uID;
        this.personalMessages = personalMessages;
        this.socialMessages = socialMessages;
        this.businessMessages = businessMessages;
        this.plus1Messages = plus1Messages;
        this.plus2Messages = plus2Messages;
        this.replyLaterMessage = replyLaterMessage;
    }

    public ArrayList<MessageCard> getPersonalMessages() {
        return personalMessages;
    }

    public void setPersonalMessages(ArrayList<MessageCard> personalMessages) {
        this.personalMessages = personalMessages;
    }

    public ArrayList<MessageCard> getSocialMessages() {
        return socialMessages;
    }

    public void setSocialMessages(ArrayList<MessageCard> socialMessages) {
        this.socialMessages = socialMessages;
    }

    public ArrayList<MessageCard> getBusinessMessages() {
        return businessMessages;
    }

    public void setBusinessMessages(ArrayList<MessageCard> businessMessages) {
        this.businessMessages = businessMessages;
    }

    public ArrayList<MessageCard> getPlus1Messages() {
        return plus1Messages;
    }

    public void setPlus1Messages(ArrayList<MessageCard> plus1Messages) {
        this.plus1Messages = plus1Messages;
    }

    public ArrayList<MessageCard> getPlus2Messages() {
        return plus2Messages;
    }

    public void setPlus2Messages(ArrayList<MessageCard> plus2Messages) {
        this.plus2Messages = plus2Messages;
    }

    private String name;

    private String uID;

    private ArrayList<MessageCard> personalMessages;

    private ArrayList<MessageCard> socialMessages;

    private ArrayList<MessageCard> businessMessages;

    private ArrayList<MessageCard> plus1Messages;

    private ArrayList<MessageCard> plus2Messages;

    private MessageCard replyLaterMessage;

    public MessageCard getReplyLaterMessage() {
        return replyLaterMessage;
    }

    public void setReplyLaterMessage(MessageCard replyLaterMessage) {
        this.replyLaterMessage = replyLaterMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}
