package org.epam.service;

import org.epam.model.AbstractEmail;

public abstract class EmailCreator {
    protected static final String TEXT = "demoText";

    public abstract AbstractEmail createEmail();
}
