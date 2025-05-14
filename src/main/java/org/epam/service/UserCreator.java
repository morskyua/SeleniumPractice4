package org.epam.service;

import org.epam.model.User;
import org.epam.util.PropertyReader;

public class UserCreator {
    private static final PropertyReader propertyReader = PropertyReader.getEnvProperties();
    public static final String USER_NAME = propertyReader.getProperty("login");
    public static final String USER_PASSWORD = propertyReader.getProperty("password");

    public static User withCredentialsFromProperty() {
        return new User(USER_NAME, USER_PASSWORD);
    }
}
