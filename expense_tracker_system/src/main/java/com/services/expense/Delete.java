package com.services.expense;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.DBConnection.expense.DBConnection;
import com.todo.expense.delete;

public class Delete implements delete {
	static String deleteString = "DELETE FROM tblexpenses WHERE expense_id = ? AND userId = ?";
	@Override
	public void delete(int expenseId, int userId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		PreparedStatement statement = DBConnection.getConnection().prepareStatement(deleteString);

		statement.setInt(1, expenseId);
		statement.setInt(2, userId);
		int rowsAffected = statement.executeUpdate();

		if (rowsAffected > 0) {
			System.out.println("Expense deleted successfully");
			System.out.println("***************************************");
		} else {
			System.out.println("Expense not found or deletion failed");
			System.out.println("***************************************");
		}
	}
}