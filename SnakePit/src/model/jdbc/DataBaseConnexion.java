package model.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import Errors.SQLConnectionException;



public abstract class DataBaseConnexion {
	
	public String HOST;
	public int PORT;
	public String DB;
	public String USER;
	public String password;
	Connection con;
	Statement stmt;
	
	
	public DataBaseConnexion() throws SQLConnectionException {
		
		configReader();
		String connectionString = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB;


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
		} catch(ClassNotFoundException e) {
			System.out.println("Can't load the Driver");
		}
			
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB, USER, password);
			stmt = con.createStatement();
			System.out.println("Database connection successfull");
		} catch (SQLException e) {
			throw new SQLConnectionException(connectionString);
		}
	}
	
	
	
	private void configReader() {
		String configFilePath = "./src/config.ini";
		Properties props = new Properties();
		
		
		try (FileInputStream in = new FileInputStream(configFilePath)) {
		    props.load(in);
		    
		    this.HOST = (String) props.get("host");
		    this.PORT = Integer.valueOf( (String)  props.get("port"));
		    this.DB = (String) props.get("db");
		    this.USER = (String) props.get("user");
		    this.password = (String) props.get("password");
		    
		} catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
	
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
