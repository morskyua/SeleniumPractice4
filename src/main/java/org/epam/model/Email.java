package org.epam.model;

public class Email extends AbstractEmail {
    private final String recipient;
    private final String subject;

    public Email(String recipient, String subject, String text) {
        super(text);
        this.subject = subject;
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }
}
