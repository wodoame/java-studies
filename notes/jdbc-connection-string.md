
// Basic format
jdbc:postgresql://localhost:5432/mydb

// With credentials as parameters
jdbc:postgresql://localhost:5432/mydb?user=postgres&password=secret

// Or pass credentials separately to DriverManager
Connection conn = DriverManager.getConnection(
    "jdbc:postgresql://localhost:5432/mydb",
    "username",
    "password"
);

// Remote server
jdbc:postgresql://192.168.1.100:5432/production_db

// SSL connection
jdbc:postgresql://localhost:5432/mydb?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory
