package org.epam.service;

import org.epam.model.AbstractEmail;
import org.epam.model.Email;
import org.epam.model.EmailReply;

public class EmailReplyCreator extends EmailCreator {
    @Override
    public AbstractEmail createEmail() {
        return new EmailReply("DemoReplyAssertText",
                new Email("demonamedemosurnname@gmail.com", "DemoEmail", TEXT));
    }
}
