package pl.eduweb.java.advanced;


import java.sql.*;

public class Main {
    public static final String JDBC_URL = "jdbc:sqlite:bank.db";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            prepareDb(connection);
            printAccounts(connection);
            makeTransfer(1, 2, 10000, connection);
            printAccounts(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void prepareDb(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS accounts");
            statement.executeUpdate("CREATE TABLE accounts (id INTEGER PRIMARY KEY, balance INTEGER)");
            statement.executeUpdate("INSERT INTO accounts (id, balance) VALUES (1, 200000)");
            statement.executeUpdate("INSERT INTO accounts (id, balance) VALUES (2, 500000)");
        }
    }

    private static void printAccounts(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            System.out.println("Accounts table:");
            ResultSet rs = statement.executeQuery("SELECT * FROM accounts");
            while (rs.next()) {
                System.out.println(String.format("%d %d", rs.getLong("id"), rs.getLong("balance")));
            }
            System.out.println();
        }
    }

    private static void makeTransfer(long sourceAccountId, long targetAccountId, long amount, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?")) {
            // debit
            statement.setLong(1, -amount);
            statement.setLong(2, sourceAccountId);
            statement.executeUpdate();

            //throwCritical();

            // credit
            statement.setLong(1, amount);
            statement.setLong(2, targetAccountId);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error during transfer");
        }
    }

    private static void throwCritical() {
        throw new RuntimeException("Some critical error");
    }
}
