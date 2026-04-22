package org.example.smart_parking_260219.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum DBConnection {
    INSTANCE;

    private final HikariDataSource dataSource;

    DBConnection() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl(getOrDefault("SMART_PARKING_DB_URL", "jdbc:mariadb://localhost:3306/smart_parking_team2"));
        config.setUsername(getRequired("SMART_PARKING_DB_USER"));
        config.setPassword(getRequired("SMART_PARKING_DB_PASSWORD"));

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private String getRequired(String key) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Required environment variable is missing: " + key);
        }
        return value;
    }

    private String getOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }
}
