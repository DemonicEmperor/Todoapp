package factory;
import java.sql.Connection;

import java.sql.DriverManager;

public class DBconn {
	static Connection con;
	public static Connection getcon() {
		try {
			if(con==null) {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp","root","Maanas59*#");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
