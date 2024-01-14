package com.services.expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.DBConnection.expense.*;
import com.todo.expense.*;

public class Login implements login {
	// Assuming you have the user ID from somewhere, e.g., after login
	static int userId;  // Replace with the actual user ID

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
					System.out.println("Login successful! Now, You can use Services!");
					while (!exit) {
						System.out.println("Enter Your Choice: ");
						System.out.println("1. Add Expense");
						System.out.println("2. Update Expense");
						System.out.println("3. Delete Expense");
						System.out.println("4. Display Expense");
						System.out.println("5. Logout");

						int x = scanner.nextInt();
						switch (x) {
						case 1:
							// Pass the userId to the Create class
							System.out.println("**********You have choose Add Expense option**********");
							insert c1 = new Create();
							c1.add(userId);
							break;
						case 2:
							System.out.println("**********You have choose Update Expense option**********");
							update u1 = new Update();
							System.out.println("Enter Expense ID to update:");
							int expenseIdToUpdate = scanner.nextInt();
							u1.update(expenseIdToUpdate);
							break;
						case 3:
							System.out.println("**********You have choose Delete Expense option**********");
							System.out.println("Enter Expense ID to delete:");
							int expenseId = scanner.nextInt();
							delete d2 = new Delete();
							d2.delete(expenseId, userId);
							break;
						case 4:
							System.out.println("**********You have choose Display Expense option**********");
							display d1 = new Display();
							d1.display(userId);
							break;
						case 5:
							exit = true;
							System.out.println("Log out from system successful. Goodbye!");
							System.out.println("***************************************");
							break;
						default:
							System.out.println("Invalid option. Please try again.");
							System.out.println("***************************************");
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