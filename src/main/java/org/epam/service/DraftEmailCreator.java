package org.epam.service;

import org.epam.model.AbstractEmail;
import org.epam.model.Email;

public class DraftEmailCreator extends EmailCreator {
    @Override
    public AbstractEmail createEmail() {
        return new Email("demoAddress", "demoDraftSubject", TEXT);
    }

}
