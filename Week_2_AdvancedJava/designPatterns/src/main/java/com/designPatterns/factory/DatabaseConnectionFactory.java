package com.designPatterns.factory;

public class DatabaseConnectionFactory {
    public interface DatabaseConnection {
        void connect();
        void disconnect();
    }

    private static class MySQLConnection implements DatabaseConnection {
        @Override
        public void connect() {
            System.out.println("Connecting to MySQL database");
        }

        @Override
        public void disconnect() {
            System.out.println("Disconnecting from MySQL database");
        }
    }

    private static class PostgreSQLConnection implements DatabaseConnection {
        @Override
        public void connect() {
            System.out.println("Connecting to PostgreSQL database");
        }

        @Override
        public void disconnect() {
            System.out.println("Disconnecting from PostgreSQL database");
        }
    }

    public static DatabaseConnection createConnection(String type) {
        if (type.equalsIgnoreCase("mysql")) {
            return new MySQLConnection();
        } else if (type.equalsIgnoreCase("postgresql")) {
            return new PostgreSQLConnection();
        }
        throw new IllegalArgumentException("Unknown database type");
    }

    public static void main(String[] args) {
        DatabaseConnection mysqlConn = DatabaseConnectionFactory.createConnection("mysql");
        DatabaseConnection postgresConn = DatabaseConnectionFactory.createConnection("postgresql");

        mysqlConn.connect();
        mysqlConn.disconnect();

        postgresConn.connect();
        postgresConn.disconnect();
    }
}