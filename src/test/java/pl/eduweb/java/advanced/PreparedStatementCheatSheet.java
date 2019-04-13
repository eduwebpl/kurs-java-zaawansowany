package pl.eduweb.java.advanced;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PreparedStatementCheatSheet {

    public static final String JDBC_URL = "jdbc:sqlite:employees.db";

    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        // to make tests cleaner - don't do it in production code
        connection = DriverManager.getConnection(JDBC_URL);
        createDatabase();
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void sqlInjectionExample() throws SQLException {
        var noteProvidedByUser = "' || (SELECT MAX(id) || ' ' || MAX(value) FROM secrets GROUP BY value, id)) --";
        Statement statement = getStatement();
        String sql = "INSERT INTO employees_notes (value) VALUES ('" + noteProvidedByUser + "')";
        statement.executeUpdate(sql);
        printEmployeesNotes();
    }

    @Test
    void sqlInjectionPreventionUsingPreparedStatement() throws Exception {
        var noteProvidedByUser = "' || (SELECT MAX(id) || ' ' || MAX(value) FROM secrets GROUP BY value, id)) --";
        String sql = "INSERT INTO employees_notes (value) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, noteProvidedByUser);
        preparedStatement.executeUpdate();
        printEmployeesNotes();
    }

    @Test
    void collectionInsertion() throws Exception {
        var generatedNotes = IntStream.range(1, 10).mapToObj(i -> "note" + i).collect(Collectors.toList());

        String sql = "INSERT INTO employees_notes (value) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        for (String note : generatedNotes) {
            statement.setString(1, note); // prepared statement can be reused to insert multiple records
            statement.executeUpdate(); // fire to db
        }

        printEmployeesNotes();
    }

    @Test
    void batchCollectionInsertion() throws Exception {
        var generatedNotes = IntStream.range(1, 10).mapToObj(i -> "note" + i).collect(Collectors.toList());

        String sql = "INSERT INTO employees_notes (value) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        for (String note : generatedNotes) {
            statement.setString(1, note);
            statement.addBatch();
        }
        statement.executeBatch(); // fire to db

        printEmployeesNotes();
    }

    private Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    private void createDatabase() throws SQLException {
        Statement statement = getStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS EMPLOYEES");
        statement.executeUpdate("CREATE TABLE employees (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING)");
        statement.executeUpdate("DROP TABLE IF EXISTS employees_notes");
        statement.executeUpdate("CREATE TABLE employees_notes (id INTEGER PRIMARY KEY AUTOINCREMENT, value STRING)");
        statement.executeUpdate("DROP TABLE IF EXISTS secrets");
        statement.executeUpdate("CREATE TABLE secrets (id INTEGER PRIMARY KEY AUTOINCREMENT, value STRING)");

        statement.executeUpdate("INSERT INTO employees (name) VALUES ('John')");
        statement.executeUpdate("INSERT INTO employees (name) VALUES ('Kate')");
        statement.executeUpdate("INSERT INTO employees (name) VALUES ('Jack')");
        statement.executeUpdate("INSERT INTO secrets (value) VALUES ('Secret1234')");
    }

    private void printEmployeesNotes() throws SQLException {
        Statement statement = getStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees_notes");
        System.out.println("employees_notes table content:");
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("value");
            System.out.println(String.format("id=%d value=%s", id, name));
        }
    }
}