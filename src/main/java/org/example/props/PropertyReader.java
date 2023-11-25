package org.example.props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public class PropertyReader {
//    public static void main(String[] args) {
//        System.out.println(getConnectionUrlForPostgres());
//    }

    public static Optional<String> getConnectionUrlForPostgres() {
        return getProperties()
                .map(properties -> properties.getProperty("hibernate.connection.url"));
    }

    public static Optional<String> getUserForPostgres() {
        return getProperties()
                .map(properties -> properties.getProperty("hibernate.connection.username"));
    }

    public static Optional<String> getPasswordForPostgres() {
        return getProperties()
                .map(properties -> properties.getProperty("hibernate.connection.password"));
    }

    /* Helpers */

    private static Optional<Properties> getProperties() {
        try (InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream("hibernate.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find hibernate.properties");
                return Optional.empty();
            }

            prop.load(input);

            return Optional.of(prop);
        } catch (IOException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
