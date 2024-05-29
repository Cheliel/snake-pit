package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseConnexion {
	
	final String HOST = "localhost";
	final int PORT = 3306;
	final String DB = "snakePit";
	Connection con;
	Statement stmt;
	
	
	public DataBaseConnexion() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
		} catch(ClassNotFoundException e) {
			System.out.println("Can't load the Driver");
		}
		
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB,"root","");
			stmt = con.createStatement();

			System.out.println("Database connection successfull");
		} catch (SQLException e) {
			System.out.println("Can't connect to Database");
		}
	}
	
	
	public void closeConnexion() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnexion() {
		return con;
	}
	
	public Statement getSTMT() {
		return stmt;
	}

}
