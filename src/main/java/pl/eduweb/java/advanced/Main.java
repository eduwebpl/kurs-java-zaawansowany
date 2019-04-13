package pl.eduweb.java.advanced;


import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static final String JDBC_URL = "jdbc:sqlite:bank.db";
    public static final String WHOLE_FAILURE_MESSAGE = "More than 50% of minor transactions failed - all transactions will be rollbacked";
    public static final String PARTIAL_SUCCESS_MESSAGE = "More than 50% of minor transactions succeed - success transactions will be committed";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            prepareDb(connection);
            printAccounts(connection);
            makeTransfers(1, Arrays.asList(2L, 3L, 4L), 10000, connection);
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
            statement.executeUpdate("INSERT INTO accounts (id, balance) VALUES (2, 10000)");
            statement.executeUpdate("INSERT INTO accounts (id, balance) VALUES (3, 10000)");
            statement.executeUpdate("INSERT INTO accounts (id, balance) VALUES (4, 10000)");
            statement.executeUpdate("INSERT INTO accounts (id, balance) VALUES (5, 10000)");
        }
    }

    private static void makeTransfers(long sourceAccountId, List<Long> targetAccountsIds, long amount, Connection connection) throws SQLException {
        long failuresCounter = 0;
        try {
            for (long targetId : targetAccountsIds) {
                var success = makeSingleTransfer(sourceAccountId, targetId, amount, connection);
                failuresCounter += success ? 0 : 1;
            }
        } finally {
            try {
                if (failuresCounter * 2 >= targetAccountsIds.size()) {
                    System.out.println(WHOLE_FAILURE_MESSAGE);
                    connection.rollback();
                } else {
                    System.out.println(PARTIAL_SUCCESS_MESSAGE);
                    connection.commit();
                }
            } finally {
                connection.setAutoCommit(false);
            }
        }
    }

    private static boolean makeSingleTransfer(long sourceAccountId, long targetId, long amount, Connection connection) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        Savepoint savepoint = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            savepoint = connection.setSavepoint();
            statement.setLong(1, -amount);
            statement.setLong(2, sourceAccountId);
            statement.executeUpdate();

            sometimesThrowCritical(targetId);

            statement.setLong(1, amount);
            statement.setLong(2, targetId);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            if (savepoint != null) {
                connection.rollback(savepoint);
            }
            System.out.println(String.format("Single transaction rollbacked %d -> %d", sourceAccountId, targetId));
        }
        return false;
    }

    private static void sometimesThrowCritical(long targetId) {
        if (targetId % 2 == 0) {
            throw new RuntimeException("Some critical error");
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
}
