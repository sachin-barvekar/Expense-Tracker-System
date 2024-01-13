package com.todo.expense;

import java.sql.SQLException;

public interface insert {
	public void add(int userId) throws ClassNotFoundException, SQLException;
}
