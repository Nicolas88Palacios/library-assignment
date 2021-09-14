import java.sql.Connection;
import java.sql.DriverManager;
//import java.util.*;
public class DBConnect{
  private static final String driver = "com.mysql.jdbc.Driver";
  private static final String url = "jdbc:mysql://localhost:3306/library";
  private static final String user = "root";
  private static final String password = "Chico88.root.03";
    public Connection con(){
        Connection con = null;
      try {
          Class.forName(driver);
          con = DriverManager.getConnection(url, user, password);
          if (con == null) {
              System.out.println("Connection cannot be established");
          }
          return con;
      } catch (Exception e) {
          System.out.println(e);
      }
      return null;
      } 
}