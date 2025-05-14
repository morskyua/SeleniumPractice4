package org.epam.model;

public class Email {
    private String recipient;
    private String subject;
    private String draftText;

    public Email(String recipient, String subject, String draftText) {
        this.recipient = recipient;
        this.subject = subject;
        this.draftText = draftText;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDraftText() {
        return draftText;
    }

    public void setDraftText(String draftText) {
        this.draftText = draftText;
    }

    @Override
    public String toString() {
        return "DraftEmail{" +
                "recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", draftText='" + draftText + '\'' +
                '}';
    }
}
