package com.client.expense;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import com.services.expense.Create;
import com.services.expense.Display;
import com.services.expense.Login;
import com.services.expense.Register;
import com.todo.expense.*;


public class Client {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		Scanner s1=new Scanner(System.in);
		System.out.println("**********Welcome to the Expense Tracker System**********");
		while (true) {
			System.out.println("1. Sign Up");
			System.out.println("2. Log in");
			System.out.print("Choose an option:");

			int ch = s1.nextInt();

			switch (ch) {
			case 1:
				System.out.println("**********You choose Sign up option**********");
				register r1 = new Register();
				r1.register();
				break;

			case 2:
				System.out.println("**********You choose Log in option**********");
				login l1 = new Login();
				l1.login();
				break;
			}
		}

	}
}