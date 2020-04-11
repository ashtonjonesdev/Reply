package dev.ashtonjones.reply.datamodels;


import java.io.Serializable;

public class MessageCard implements Serializable {

    private String title;

    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageCard(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public MessageCard() {
    }
}
