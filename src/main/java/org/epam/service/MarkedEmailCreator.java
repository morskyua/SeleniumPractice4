package org.epam.service;

import org.epam.model.AbstractEmail;
import org.epam.model.MarkedEmail;

public class MarkedEmailCreator extends EmailCreator {
    @Override
    public AbstractEmail createEmail() {
        return new MarkedEmail("demoAddress", "DemoEmail", TEXT, true);
    }
}
