package com.DBConnection.expense;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/expense_tracker";
		String user = "root";
		String pass = "?";
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, pass);
		return connection;
	}
}
