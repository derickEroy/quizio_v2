package com.dak.db;

import com.dak.configs.EnvironmentVariable;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final MysqlDataSource dataSource;

    static {
        dataSource = new MysqlDataSource();
        dataSource.setServerName(EnvironmentVariable.DATABASE_HOST);
        dataSource.setPortNumber(EnvironmentVariable.DATABASE_PORT);
        dataSource.setDatabaseName(EnvironmentVariable.DATABASE_NAME);
        dataSource.setUser(EnvironmentVariable.DATABASE_USER);
        dataSource.setPassword(EnvironmentVariable.DATABASE_PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Runs all pending migrations on the database.
     *
     * <p>Note: For best practice, consider integrating this functionality as a separate
     * console command instead of invoking it directly within the application code.</p>
     */
    public static void migrate() {
        Flyway flyway = Flyway.configure()
            .dataSource(
                EnvironmentVariable.DATABASE_URL,
                EnvironmentVariable.DATABASE_USER,
                EnvironmentVariable.DATABASE_PASSWORD
            )
            .locations("classpath:db/migration")
            .load();

        flyway.migrate();
    }
}