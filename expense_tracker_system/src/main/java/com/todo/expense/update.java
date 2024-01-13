package com.todo.expense;

import java.sql.SQLException;

public interface update {
	public void update(int expenseIdToUpdate) throws ClassNotFoundException, SQLException;
}
