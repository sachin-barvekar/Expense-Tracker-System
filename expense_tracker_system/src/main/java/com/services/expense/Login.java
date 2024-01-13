package com.services.expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.DBconnection.demo.DBConnection;
import com.todo.expense.*;

public class Login implements login {
	// Assuming you have the user ID from somewhere, e.g., after login
	int userId;  // Replace with the actual user ID

	public void login() throws ClassNotFoundException, SQLException {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		System.out.println("Enter the user email:");
		String useremail = scanner.next();

		System.out.println("Enter password:");
		String password = scanner.next();

		String query = "SELECT * FROM tbluser WHERE useremail=? AND password=?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, useremail);
			preparedStatement.setString(2, password);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					userId = resultSet.getInt("userId");
					System.out.println("Login successful!");
					while (!exit) {
						System.out.println("Enter Your choice");
						System.out.println("1. Add Expense");
						System.out.println("2. Update Expense");
						System.out.println("3. Delete Expense");
						System.out.println("4. Display Expense");
						System.out.println("5. Logout");

						int x = scanner.nextInt();
						switch (x) {
						case 1:
							// Pass the userId to the Create class
							insert c1 = new Create();
							c1.add(userId);
							break;
						case 2:
							update u1 = new Update();
							System.out.println("Enter Expense ID to update:");
							int expenseIdToUpdate = scanner.nextInt();
							u1.update(expenseIdToUpdate);
							break;
						case 3:
							System.out.println("Enter Expense ID to delete:");
							int expenseId = scanner.nextInt();
							delete d2 = new Delete();
							d2.delete(expenseId);
							break;
						case 4:
							display d1 = new Display();
							d1.display(userId);
							break;
						case 5:
							exit = true;
							System.out.println("Log out from system successful. Goodbye!");
							break;
						default:
							System.out.println("Invalid option. Please try again.");
						}
					}
				} else {
					System.out.println("Invalid username or password. Please try again.");
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error connecting to the database.");
		}
	}
}