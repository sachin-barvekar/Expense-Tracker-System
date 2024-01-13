package com.services.expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.todo.expense.*;
import com.DBConnection.expense.DBConnection;

public class Register implements register{
	private Map<String, String> userDatabase = new HashMap<>();

	public void register() throws ClassNotFoundException, SQLException{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Email:");
		String useremail = scanner.next();

		if (userDatabase.containsKey(useremail)) {
			System.out.println("User already exists for these mail. Please choose another one.");
			return;
		}

		System.out.println("Enter User Name:");
		String username = scanner.next();

		System.out.println("Enter password:");
		String password = scanner.next();

		userDatabase.put(useremail,password);



		try {
			saveToDatabase(useremail, username, password);
			System.out.println("Sign up successful! You can now log in with your new credentials.");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error connecting to the database or saving to the database.");
		}
	}

	private void saveToDatabase(String useremail, String username, String password) throws ClassNotFoundException, SQLException {
		String query = "INSERT INTO tbluser (useremail, username, password) VALUES (?, ?, ?)";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, useremail);
			preparedStatement.setString(2, username);            
			preparedStatement.setString(3, password);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error saving to the database.");
		}
	}
}