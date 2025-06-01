package org.epam.model;

public class EmailReply extends AbstractEmail {
    private final Email target;

    public EmailReply(String text, Email target) {
        super(text);
        this.target = target;
    }

    public Email getTarget() {
        return target;
    }

    @Override
    public String getSubject() {
        return target.getSubject();
    }

    @Override
    public String getRecipient() {
        return target.getRecipient();
    }
}
