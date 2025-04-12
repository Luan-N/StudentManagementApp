import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DatabaseUtilities {

    private static final String DB_FILE_PATH = "mydatabase.db";// we will use one single database for the entire app

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + DB_FILE_PATH;
        return DriverManager.getConnection(url);
    }
    // Initialize the database and tables
    static {
        initializeDatabase();
    }

    private static void initializeDatabase() {
        String clearUsersSQL = "DROP TABLE IF EXISTS users";// clear the database
        String initializeUsersSQL = "CREATE TABLE users (username VARCHAR(30) PRIMARY KEY, password TEXT NOT NULL)";// initilize the database

        String[][] dummyUsers = {
                { "john_doe", "password123" },
                { "alice_smith", "alice_pass" },
                { "bob_jones", "bob1234" },
                { "charlie_brown", "charlie321" },
                { "eve_white", "evepass" }
        };

        try (Connection conn = getConnection();
                java.sql.Statement stmt = conn.createStatement()) {
            // Clear existing tables and initialize the new tables
            stmt.execute(clearUsersSQL);
            stmt.execute(initializeUsersSQL);
            System.out.println("Database and tables initialized");

            // Insert dummy users
            for (String[] user : dummyUsers) {
                String insertSQL = "INSERT INTO users (username, password) VALUES ('" + user[0] + "', '" + user[1]+ "')";
                stmt.executeUpdate(insertSQL);
            }
            System.out.println("Dummy datas inserted successfully");

        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    //retrive the password of the user(for login)
    public static String getPasswordByUsername(String username) {
        String sql = "SELECT username, password FROM users WHERE username = ?";// query to select password by matching username
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");// return the password
            }
            return null;// no password matches username
        } catch (SQLException e) {
            System.err.println("Error getting password by username: " + e.getMessage());
            return null;// an error has occured
        }
    }

    //add registered user
    public static boolean addUser(String username, String password) {
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // add user to the table of users
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // user already exists or other errors
            System.err.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    // public static boolean deleteUser(String username) {
    // String sql = "DELETE FROM users WHERE username = ?";
    // try (Connection conn = getConnection();
    // PreparedStatement pstmt = conn.prepareStatement(sql)) {
    // pstmt.setString(1, username);
    // int rowsAffected = pstmt.executeUpdate();
    // return rowsAffected > 0;
    // } catch (SQLException e) {
    // System.err.println("Error deleting user: " + e.getMessage());
    // return false;
    // }
    // }
}