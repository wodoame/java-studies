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
public class JDBC {
// General format: jdbc:postgresql://[host]:[port]/[database]?[parameters]
// jdbc:postgresql://localhost:5432/mydb?user=postgres&password=secret
 try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:")){
    //
 }
}
