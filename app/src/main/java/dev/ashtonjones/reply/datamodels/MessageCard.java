package dev.ashtonjones.reply.datamodels;

public class MessageCard {

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
}
