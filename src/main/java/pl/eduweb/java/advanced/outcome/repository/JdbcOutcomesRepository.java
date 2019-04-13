package pl.eduweb.java.advanced.outcome.repository;

import pl.eduweb.java.advanced.outcome.Outcome;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcOutcomesRepository implements OutcomesRepository, AutoCloseable {

    private static final OffsetDateTime SQLITE_MIN_SUPPORTED_DATETIME = OffsetDateTime.parse("0000-01-01T00:00:00.000Z");
    private static final OffsetDateTime SQLITE_MAX_SUPPORTED_DATETIME = OffsetDateTime.parse("9999-12-31T23:59:59.999Z");

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS outcomes " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, comment TEXT, amount INTEGER, datetime TEXT)";

    private static final String INSERT_SQL = "INSERT INTO outcomes (comment, amount, datetime) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE outcomes SET comment = ?, amount = ?, datetime = ? WHERE id = ?";
    private static final String SELECT_ONE_SQL = "SELECT * FROM outcomes WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM outcomes";
    private static final String SELECT_SUM_SQL = "SELECT SUM(amount) FROM outcomes WHERE datetime(datetime) BETWEEN datetime(?) AND datetime(?)";
    private static final String DELETE_ALL_SQL = "DELETE FROM outcomes";
    private static final String DELETE_SINGLE_SQL = "DELETE FROM outcomes WHERE id = ?";

    private final String url;
    private Connection connection;

    public JdbcOutcomesRepository(Path databasePath) throws Exception {
        url = "jdbc:sqlite:" + databasePath.toAbsolutePath();
        try {
            initTable();
        } catch (Exception e) {
            close();
            throw e;
        }
    }

    private void initTable() throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(CREATE_TABLE_SQL);
        }
    }

    private Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                this.connection = DriverManager.getConnection(url);
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception during connection retrieving", e);
        }
        return connection;
    }

    @Override
    public void add(Outcome outcome) {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_SQL)) {
            statement.setString(1, outcome.getComment());
            statement.setLong(2, outcome.getAmount().movePointRight(2).longValueExact());
            statement.setString(3, outcome.getTime().toString());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Exception during inserting new outcome", e);
        }
    }

    @Override
    public void addAll(List<Outcome> outcomes) {
        var connection = getConnection();
        setAutoCommit(connection, false);
        try {
            outcomes.forEach(this::add);
            connection.commit();
        } catch (Exception e) {
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException("Error during making rollback", e);
        }
    }

    private void setAutoCommit(Connection connection, boolean autoCommit) {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new RuntimeException("Exception during setting autocommit", e);
        }
    }

    @Override
    public boolean update(Outcome outcome) {
        try (var statement = getConnection().prepareStatement(UPDATE_SQL)) {
            statement.setString(1, outcome.getComment());
            statement.setLong(2, outcome.getAmount().movePointRight(2).longValueExact());
            statement.setString(3, outcome.getTime().toString());
            statement.setLong(4, outcome.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException("Exception during updating outcome", e);
        }
    }

    @Override
    public boolean delete(long id) {
        try (var statement = getConnection().prepareStatement(DELETE_SINGLE_SQL)) {
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException("Exception during deleting outcome", e);
        }
    }

    @Override
    public int deleteAll() {
        try (var statement = getConnection().prepareStatement(DELETE_ALL_SQL)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Exception during deleting outcomes", e);
        }
    }

    @Override
    public List<Outcome> getAll() {
        try (Statement statement = getConnection().createStatement()) {
            var resultSet = statement.executeQuery(SELECT_ALL_SQL);
            return mapToObjects(resultSet);
        } catch (Exception e) {
            throw new RuntimeException("Exception during selecting outcomes", e);
        }
    }

    @Override
    public Optional<Outcome> getOne(long id) {
        try (var statement = getConnection().prepareStatement(SELECT_ONE_SQL)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapToObject(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception during selecting outcomes", e);
        }
    }

    private ArrayList<Outcome> mapToObjects(ResultSet resultSet) throws SQLException {
        var outcomes = new ArrayList<Outcome>();
        while (resultSet.next()) {
            outcomes.add(mapToObject(resultSet));
        }
        return outcomes;
    }

    private Outcome mapToObject(ResultSet resultSet) throws SQLException {
        var id = resultSet.getLong("id");
        var comment = resultSet.getString("comment");
        var datetime = OffsetDateTime.parse(resultSet.getString("datetime"));
        var amount = BigDecimal.valueOf(resultSet.getLong("amount")).movePointLeft(2);
        return new Outcome(id, comment, datetime, amount);
    }

    @Override
    public BigDecimal computeSum(OffsetDateTime fromDateNullable, OffsetDateTime toDateNullable) {
        try (var statement = getConnection().prepareStatement(SELECT_SUM_SQL)) {
            OffsetDateTime fromDate = Optional.ofNullable(fromDateNullable).orElse(SQLITE_MIN_SUPPORTED_DATETIME);
            OffsetDateTime toDate = Optional.ofNullable(toDateNullable).orElse(SQLITE_MAX_SUPPORTED_DATETIME);
            statement.setString(1, fromDate.toString());
            statement.setString(2, toDate.toString());
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new BigDecimal(resultSet.getLong(1)).movePointLeft(2);
            } else {
                throw new RuntimeException("Empty result set");
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception during computing sum of outcomes", e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
