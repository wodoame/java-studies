package jdbccons;
// Java Database Connectivity
// jdbccons.JDBC API provides industry-standard and database-independent
// connectivity between Java applications and relational database servers
// (relational databases, spreadsheets, and flat files)

// jdbccons.JDBC Driver
// A jdbccons.JDBC driver is a set of Java Classes that implement the jdbccons.JDBC interfaces, targeting a specific database.

// Steps to connect to the database
// 1. Import jdbccons.JDBC packages
// 2. Open a connection to the database
// 3. Create a statement object to perform a query
// 4. Execute the statement object and return the query resultset
// 5. Process the resultset

import java.sql.*;

/**
 * Main application class demonstrating CRUD operations using StudentDAO.
 */
public class JDBC {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        try {
            // Example: READ - Select all students
//            System.out.println("=== SELECT ALL STUDENTS ===");
//            resultSet = studentDAO.selectAllStudents();
//            studentDAO.displayResultSet(resultSet);
//            System.out.println();

            // Example: CREATE - Insert a new student (uncomment to test)
             System.out.println("=== CREATE NEW STUDENT ===");
             int rowsAffected = studentDAO.createStudent("Miriam", "Wodoame", "miriam@gmail.com", 16);
             System.out.println();

            // Example: READ - Select student by ID (uncomment to test)
            // System.out.println("=== SELECT STUDENT BY ID ===");
            // resultSet = studentDAO.selectStudentById(1);
            // studentDAO.displayResultSet(resultSet);
            // System.out.println();

            // Example: UPDATE - Update a student (uncomment to test)
            // System.out.println("=== UPDATE STUDENT ===");
            // studentDAO.updateStudent(1, "John Updated", "john.updated@example.com");
            // System.out.println();

            // Example: DELETE - Delete a student (uncomment to test)
            // System.out.println("=== DELETE STUDENT ===");
            // studentDAO.deleteStudent(4);
            // System.out.println();

        } catch (SQLException e) {
            System.err.println("Database operation failed!");
            e.printStackTrace();
        } finally {
            // Clean up resources
            studentDAO.closeResources(resultSet, statement, connection);
            System.out.println("Connection closed.");
        }
    }
}
