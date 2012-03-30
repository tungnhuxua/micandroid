package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
	
	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:db/test.db");
			Statement stmt = conn.createStatement();
			String sql = "insert into user values(1, 'test', 'coffee')";
			stmt.execute(sql);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
