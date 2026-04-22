package org.example.smart_parking_260219;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    private static String envOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }

    private static String envRequired(String key) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Required environment variable is missing: " + key);
        }
        return value;
    }

    @Test
    public void connectionTest() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = envOrDefault("SMART_PARKING_DB_URL", "jdbc:mariadb://localhost:3306/smart_parking_team2");
            String user = envRequired("SMART_PARKING_DB_USER");
            String pass = envRequired("SMART_PARKING_DB_PASSWORD");

            Connection connection = DriverManager.getConnection(url, user, pass);
            // 변수가 null이 아닌지 확인 -> null이 아니면 객체를 참조
            Assertions.assertNotNull(connection);
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl(envOrDefault("SMART_PARKING_DB_URL", "jdbc:mariadb://localhost:3306/smart_parking_team2"));
        config.setUsername(envRequired("SMART_PARKING_DB_USER"));
        config.setPassword(envRequired("SMART_PARKING_DB_PASSWORD"));

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource dataSource = new HikariDataSource(config);

        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connection success: " + connection);
            // 변수가 null이 아닌지 확인 -> null이 아니면 객체를 참조
            Assertions.assertNotNull(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
