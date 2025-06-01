package org.epam.model;

public abstract class AbstractEmail {
    private final String text;

    protected AbstractEmail(String draftText) {
        this.text = draftText;
    }

    public String getText() {
        return text;
    }

    public abstract String getSubject();

    public abstract String getRecipient();
}
