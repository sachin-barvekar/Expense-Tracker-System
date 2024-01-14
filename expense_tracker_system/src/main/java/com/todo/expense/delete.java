package com.todo.expense;

import java.sql.SQLException;

public interface delete {
	public void delete(int expenseId, int userId) throws ClassNotFoundException, SQLException;
}
