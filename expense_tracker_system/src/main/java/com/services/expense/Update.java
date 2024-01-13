package com.services.expense;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.DBConnection.expense.DBConnection;
import com.todo.expense.update;

public class Update implements update {
	static String selectExpenseString = "SELECT * FROM tblexpenses WHERE expense_Id=?";
	static String updateString = "UPDATE tblexpenses SET category=?, description=?, amount=?, expense_date=?, payment_method=? WHERE expense_Id=?";

	public void update(int expenseIdToUpdate) throws ClassNotFoundException, SQLException {
		// Check if the expenseId exists before attempting the update
		if (isExpenseIdExists(expenseIdToUpdate)) {
			// Continue with the update
			PreparedStatement statement = DBConnection.getConnection().prepareStatement(updateString);

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter new Expense Category:");
			String category = sc.next();

			System.out.println("Enter new Expense Description: ");
			String description = sc.next();

			System.out.println("Enter new Amount: ");
			int amount = sc.nextInt();

			System.out.println("Enter new Date (YYYY-MM-DD): ");
			String date = sc.next();

			System.out.println("Enter new Payment Method: ");
			String payment_method = sc.next();

			statement.setString(1, category);
			statement.setString(2, description);
			statement.setInt(3, amount);
			statement.setString(4, date);
			statement.setString(5, payment_method);
			statement.setInt(6, expenseIdToUpdate); // Set the expense ID obtained from the parameter
			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Expense updated successfully");
			} else {
				System.out.println("Expense not found or update failed");
			}
		} else {
			System.out.println("Expense ID does not exist. Update aborted.");
		}
	}

	private boolean isExpenseIdExists(int expenseId) throws SQLException, ClassNotFoundException {
		try (PreparedStatement selectStatement = DBConnection.getConnection().prepareStatement(selectExpenseString)) {
			selectStatement.setInt(1, expenseId);
			try (ResultSet resultSet = selectStatement.executeQuery()) {
				return resultSet.next(); // Returns true if the expenseId exists, false otherwise
			}
		}
	}
}
