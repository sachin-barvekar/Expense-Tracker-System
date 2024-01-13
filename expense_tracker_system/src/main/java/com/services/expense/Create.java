package com.services.expense;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.DBConnection.expense.DBConnection;
import com.todo.expense.insert;

public class Create implements insert {
	static String insertString = "INSERT INTO tblexpenses(category, description, amount, expense_date, payment_method, userid) VALUES(?,?,?,?,?,?)";

	public void add(int userId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(insertString);

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Expense Category:");
		String category = sc.next();

		System.out.println("Enter Expense Description: ");
		String description = sc.next();

		System.out.println("Enter Amount: ");
		int amount = sc.nextInt();

		System.out.println("Enter Date (YYYY-MM-DD): ");
		String date = sc.next();

		System.out.println("Enter Payment Method: ");
		String payment_method = sc.next();

		statement.setString(1, category);
		statement.setString(2, description);
		statement.setInt(3, amount);
		statement.setString(4, date);
		statement.setString(5, payment_method);
		statement.setInt(6, userId); // Set the user ID obtained from the parameter
		statement.executeUpdate();
		System.out.println("Expense added successfully");
	}
}
