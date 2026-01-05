package jdbccons;

import java.sql.*;

/**
 * Data Access Object (DAO) for Student CRUD operations.
 * Handles all database interactions for the students table.
 */
public class StudentDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/testjdbc";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Bernardxx2003";

    /**
     * Establishes a connection to the PostgreSQL database.
     *
     * @return Connection object
     * @throws SQLException if connection fails
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
     * CREATE: Inserts a new student into the database.
     *
     * @param firstName student first name
     * @param lastName student last name
     * @param email student email
     * @return number of rows affected
     * @throws SQLException if database access error occurs
     */
    public int createStudent(String firstName, String lastName, String email, int age) throws SQLException {
        String query = "INSERT INTO students (first_name, last_name, email, age) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setInt(4, age);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Student created successfully! Rows affected: " + rowsAffected);
            return rowsAffected;
        }
    }

    /**
     * READ: Retrieves all students from the database.
     *
     * @return ResultSet containing all students
     * @throws SQLException if database access error occurs
     */
    public ResultSet selectAllStudents() throws SQLException {
        String query = "SELECT * FROM students";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    /**
     * READ: Retrieves a specific student by ID.
     *
     * @param id student ID
     * @return ResultSet containing the student
     * @throws SQLException if database access error occurs
     */
    public ResultSet selectStudentById(int id) throws SQLException {
        String query = "SELECT * FROM students WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeQuery();
    }

    /**
     * UPDATE: Updates an existing student's information.
     *
     * @param id student ID
     * @param name new student name
     * @param email new student email
     * @return number of rows affected
     * @throws SQLException if database access error occurs
     */
    public int updateStudent(int id, String name, String email) throws SQLException {
        String query = "UPDATE students SET name = ?, email = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, id);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Student updated successfully! Rows affected: " + rowsAffected);
            return rowsAffected;
        }
    }

    /**
     * DELETE: Removes a student from the database.
     *
     * @param id student ID
     * @return number of rows affected
     * @throws SQLException if database access error occurs
     */
    public int deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM students WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Student deleted successfully! Rows affected: " + rowsAffected);
            return rowsAffected;
        }
    }

    /**
     * Displays the contents of a ResultSet with all columns.
     *
     * @param resultSet the ResultSet to display
     * @throws SQLException if database access error occurs
     */
    public void displayResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Display column headers
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i));
            if (i < columnCount) {
                System.out.print(" | ");
            }
        }
        System.out.println();
        System.out.println("=".repeat(80));

        // Display rows
        int rowCount = 0;
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getString(i));
                if (i < columnCount) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            rowCount++;
        }

        if (rowCount == 0) {
            System.out.println("No records found.");
        } else {
            System.out.println("Total records: " + rowCount);
        }
    }

    /**
     * Closes database resources safely.
     *
     * @param resultSet ResultSet to close
     * @param statement Statement to close
     * @param connection Connection to close
     */
    public void closeResources(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}

