import java.sql.*;
public class databaseConnection {
    Connection c;
    Statement s;
    public databaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "");
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}