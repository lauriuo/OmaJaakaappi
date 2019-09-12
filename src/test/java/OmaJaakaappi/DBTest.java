package OmaJaakaappi;
import java.sql.*;

public class DBTest {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost:4444/testii";

    //  Database credentials
    static final String USER = "ryhma9";
    static final String PASS = "ryhma9";

    public static void main( String[] args )
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
                        conn = DriverManager.getConnection(
                    DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM testi");
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + " name: " + name);
            }
        } catch (Exception e) {
			e.printStackTrace();
        } finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
    }
}