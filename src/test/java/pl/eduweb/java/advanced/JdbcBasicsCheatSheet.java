package pl.eduweb.java.advanced;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcBasicsCheatSheet {

    public static final String JDBC_URL = "jdbc:sqlite:employees.db";

    @BeforeEach
    void setUp() {
        createDatabase();
    }

    @Test
    void properlyCreateCloseConnectionAndStatement() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {
            // work with statement/connection
        } catch (SQLException e) {
            // handle your exception
        }
    }

    @Test
    void selectRecord() throws Exception {
        Statement statement = getStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM employees");
        System.out.println(String.format("%d %s", rs.getLong("id"), rs.getString("name")));
    }

    @Test
    void iterateOverAllRecords() throws Exception {
        Statement statement = getStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM employees");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
    }


    @Test
    void deleteRecord() throws Exception {
        Statement statement = getStatement();
        statement.executeUpdate("DELETE FROM employees WHERE name = 'John'");
        printEmployees(statement);
    }

    @Test
    void updateRecord() throws Exception {
        Statement statement = getStatement();
        statement.executeUpdate("UPDATE employees set name = 'Jack Jr.' WHERE name = 'Jack'");
        printEmployees(statement);
    }

    private void printEmployees(Statement statement) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM employees");
        while (rs.next()) {
            System.out.println(String.format("%d %s", rs.getLong("id"), rs.getString("name")));
        }
    }

    private void createDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS employees");
            statement.executeUpdate("CREATE TABLE employees (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING)");
            statement.executeUpdate("INSERT INTO employees (name) VALUES ('John')");
            statement.executeUpdate("INSERT INTO employees (name) VALUES ('Kate')");
            statement.executeUpdate("INSERT INTO employees (name) VALUES ('Jack')");
        } catch (SQLException e) {
            throw new RuntimeException("Exception during database creation", e);
        }
    }

    // to make tests cleaner
    private Statement getStatement() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL);
        return connection.createStatement();
    }
}