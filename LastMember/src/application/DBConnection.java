package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public Connection dbConn;

	public Connection getConnection() {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbConn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
	}
}
