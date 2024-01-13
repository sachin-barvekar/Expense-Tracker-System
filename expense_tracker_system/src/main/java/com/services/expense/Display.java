package com.services.expense;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import com.DBConnection.expense.*;
import com.todo.expense.*;

public class Display implements display {
	public void display(int userId) throws ClassNotFoundException, SQLException {
		String selectString = "SELECT * FROM tblexpenses WHERE userId= ?";

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = DBConnection.getConnection().prepareStatement(selectString);
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();


			// Display the data
			while (resultSet.next()) {
				int userId1 = resultSet.getInt("userId");
				int expneseId = resultSet.getInt("expense_Id");
				String category = resultSet.getString("category");
				String description = resultSet.getString("description");
				long amount = resultSet.getLong("amount");
				Date date = resultSet.getDate("expense_date");
				String payement_method = resultSet.getString("payment_method");
				Timestamp time = resultSet.getTimestamp("created_at");
				// Display the header
				System.out.println("User ID: "+userId1+"\n"+"expneseId: "+expneseId+"\n"+"category: "+category+"\n"+"description: "+description+"\n"+"amount: "+amount+"\n"+"date: "+date + "\n"+"payement_method: "+payement_method +"\n" +"created_at: " +time+"\n");

			}
		} finally {
			// Close resources in a finally block
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
	}
}