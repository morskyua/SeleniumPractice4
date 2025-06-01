package org.epam.model;

public class MarkedEmail extends Email {
    public boolean isMarked;

    public MarkedEmail(String recipient, String subject, String text, boolean isMarked) {
        super(recipient, subject, text);
        this.isMarked = isMarked;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
