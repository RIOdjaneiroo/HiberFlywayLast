package org.example.props;

import org.flywaydb.core.Flyway;

public class DbFlywayMigration {
    private static final DbFlywayMigration INSTANCE = new DbFlywayMigration();

    private Flyway flyway;

    private DbFlywayMigration() {
        String connectionUrl = PropertyReader.getConnectionUrlForPostgres().orElseThrow();
        String username = PropertyReader.getUserForPostgres().orElseThrow();
        String password = PropertyReader.getPasswordForPostgres().orElseThrow();

        flyway = Flyway.configure()
                .dataSource(connectionUrl, username, password)
                .load();
    }

    public static DbFlywayMigration getInstance() {
        return INSTANCE;
    }

    public void migrateDatabase() {
        System.out.println("start migration");
        flyway.migrate();
        System.out.println("finish migration");
    }
}
