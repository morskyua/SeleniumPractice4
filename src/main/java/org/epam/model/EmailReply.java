package org.epam.model;

public class EmailReply {
    private Email target;
    private String text;

    public EmailReply(Email target, String text) {
        this.target = target;
        this.text = text;
    }

    public Email getTarget() {
        return target;
    }

    public void setTarget(Email target) {
        this.target = target;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
