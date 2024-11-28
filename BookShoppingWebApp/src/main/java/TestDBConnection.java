import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bookregister";
        String username = "root";
        String password = "metimysqlhawi12345#";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) {
                System.out.println("Connected to the database successfully!");
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
