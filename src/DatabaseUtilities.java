import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class DatabaseUtilities {

    private static final String DB_FILE_PATH = "mydatabase.db";// We will use one single database for the entire app

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + DB_FILE_PATH;
        Connection conn = DriverManager.getConnection(url);

        // Enable foreign key support after connection, disabled by default for sqlite
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }

        return conn;
    }

    //initialize the database upon app start(only when database is not initialized)
    public static void initializeDatabase() {
        System.out.println("Initializing database...");
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            // Load schema script
            String schemaSQL = Files.readString(Path.of("src/create_schema.sql"));
            for (String sql : schemaSQL.split(";")) {
                sql = sql.trim();
                if (!sql.isEmpty()) {
                    System.out.println("Executing SQL: " + sql + ";"); // helpful debug
                    stmt.execute(sql); 
                }
            }

            // load initial data script
            String dataSQL = Files.readString(Path.of("src/initialize_data.sql"));
            for (String sql : dataSQL.split(";")) {
                sql = sql.trim();
                if (!sql.isEmpty()) {
                    stmt.execute(sql);
                }
            }

            System.out.println("Database and tables initialized");
            System.out.println("Dummy data inserted successfully");

        } catch (SQLException | IOException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    // Retrive the password of the user(for login)
    public static String getPasswordByUsername(String username) {
        String sql = "SELECT username, password FROM User WHERE username = ?";// Query to select password by matching username
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");// Return the password
            }
            return null;// No password matches username
        } catch (SQLException e) {
            System.err.println("Error getting password by username: " + e.getMessage());
            return null;// An error has occured
        }
    }

    // Add registered user
    public static boolean addUser(String username, String password) {
        // Execute the insert statement with the provided username and password
        int rowsAffected = executeUpdate("INSERT INTO User(username, password) VALUES(?, ?)",
                new String[] { username, password });
        // Return true if one row was affected (successful insertion), false otherwise
        return rowsAffected > 0;
    }

    public static Object[][] executeQuery(String query) {
        // Execute the query and return the result as a 2D array
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            int columnCount = rs.getMetaData().getColumnCount();

            // Use an ArrayList to store the rows
            ArrayList<Object[]> rows = new ArrayList<>();

            // Iterate through the result set and build the 2D array
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int colIndex = 0; colIndex < columnCount; colIndex++) {
                    row[colIndex] = rs.getObject(colIndex + 1);
                }
                rows.add(row);
            }

            // Convert ArrayList to 2D array
            Object[][] data = new Object[rows.size()][columnCount];
            return rows.toArray(data); // Convert to 2D array
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            return null;
        }
    }

    // INSERT, UPDATE, DELETE sql statements
    // EX: executeUpdate("INSERT INTO User(username, password) VALUES(?,?)", new String[]{"username", "password"});
    public static int executeUpdate(String sql, String[] params) {
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < (params != null ? params.length : 0); i++)
                pstmt.setString(i + 1, params[i]);
            return pstmt.executeUpdate(); // Returns number of rows affected
        } catch (SQLException e) {
            System.err.println("Error executing update: " + e.getMessage());
            return -1;
        }
    }
}