package org.epam.service;

import org.epam.model.Email;

public class EmailCreator {
    private static final String TEXT = "demoText";

    public static Email createDraft() {
        return new Email("demoAddress", "demoDraftSubject", TEXT);
    }

    public static Email targetEmail() {
        return new Email("demonamedemosurnname@gmail.com", "DemoEmail", TEXT);
    }
}
