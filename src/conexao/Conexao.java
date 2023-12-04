package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private static Connection con = null;
	  private static String USERNAME = "caique";
	  private static String PASSWORD = "12345678";
	  private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	  private static String URL = "jdbc:mysql://127.0.0.1:3306/reservaDatashow";

	  public static Connection getDatabaseConnection() throws Exception{
	      Class.forName(DRIVER);
	      return con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	  }
}
