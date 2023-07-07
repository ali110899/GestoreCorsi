package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDb {

	//Gestire connessione database
	public static Connection getConnection() {
	
		String url="jdbc:mysql://localhost/iscritticorsi";
		String username="root";
		String pass="alice110899";
		
		try {
			return DriverManager.getConnection(url ,username, pass);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
