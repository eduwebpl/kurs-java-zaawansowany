package pl.eduweb.java.advanced;


import java.sql.*;

public class Main {
    public static final String JDBC_URL = "jdbc:sqlite:employees.db";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS employees");
            statement.executeUpdate("CREATE TABLE employees (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING)");
            statement.executeUpdate("INSERT INTO employees (name) VALUES ('John')");
            statement.executeUpdate("INSERT INTO employees (name) VALUES ('Kate')");
            statement.executeUpdate("INSERT INTO employees (name) VALUES ('Jack')");
            printEmployees(statement);
            
            int deleted = statement.executeUpdate("DELETE FROM employees WHERE name = 'John'");
            int updated = statement.executeUpdate("UPDATE employees set name = 'Jack Jr.'");
            System.out.println(String.format("updated: %d deleted: %d", updated, deleted));
            printEmployees(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printEmployees(Statement statement) throws SQLException {
        System.out.println("Employees table:");
        ResultSet rs = statement.executeQuery("SELECT * FROM employees");
        while (rs.next()) {
            System.out.println(String.format("%d %s", rs.getLong("id"), rs.getString("name")));
        }
        System.out.println();
    }
}
