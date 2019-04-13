package pl.eduweb.java.advanced;


import java.sql.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static final String JDBC_URL = "jdbc:sqlite:employees.db";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            recreateTables(connection);
            insertData(connection);
            workOnDb(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO employees (name) VALUES ('John')");
            statement.executeUpdate("INSERT INTO employees (name) VALUES ('Kate')");
            statement.executeUpdate("INSERT INTO employees (name) VALUES ('Jack')");
            statement.executeUpdate("INSERT INTO secrets (value) VALUES ('Secret1234')");
        }
    }

    private static void recreateTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS EMPLOYEES");
            statement.executeUpdate("CREATE TABLE employees (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING)");
            statement.executeUpdate("DROP TABLE IF EXISTS employees_notes");
            statement.executeUpdate("CREATE TABLE employees_notes (id INTEGER PRIMARY KEY AUTOINCREMENT, value STRING)");
            statement.executeUpdate("DROP TABLE IF EXISTS secrets");
            statement.executeUpdate("CREATE TABLE secrets (id INTEGER PRIMARY KEY AUTOINCREMENT, value STRING)");
        }
    }

    private static void workOnDb(Connection connection) throws SQLException {
        var notes = IntStream.range(1, 10).mapToObj(i -> "note" + i).collect(Collectors.toList());

        String sql = "INSERT INTO employees_notes (value) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (String note : notes) {
                statement.setString(1, note);
                statement.addBatch();
            }
            statement.executeBatch();
        }

        printEmployeesNotes(connection);
    }

    private static void printEmployeesNotes(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees_notes");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("value");
                System.out.println(String.format("id=%d value=%s", id, name));
            }
        }
    }
}
