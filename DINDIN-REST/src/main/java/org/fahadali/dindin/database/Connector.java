package org.fahadali.dindin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

	private final String ENDPOINT = "rds-mysql-dindin.cjw7qrfegth1.eu-west-2.rds.amazonaws.com";
	private final int PORT = 3306;
	private final String USERNAME = "fas16";
	private final String PASSWORD = "dindin123";
	private final String DATABASE = "DINDIN";
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String CONNETION_URL = "jdbc:mysql://" + ENDPOINT + ":"+ PORT +"/" + DATABASE;
	private Connection con;

	public Connector() {

		try {

			Class.forName(DRIVER);
			this.con = DriverManager.getConnection(CONNETION_URL, USERNAME, PASSWORD);
			System.out.println("Connection to database established");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public Connection getConnection() {
		return con;
	}

	public String getDatabaseName() {
		return this.DATABASE;
	}

	
	public ResultSet doQuery(String query) throws SQLException {
		PreparedStatement prepstmt = con.prepareStatement(query);
		ResultSet rs = prepstmt.executeQuery();
		return rs;
	}

	
	public void doUpdate(String query) throws SQLException {
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
	}


	public PreparedStatement doPreparedStmt(String query) throws SQLException {
		PreparedStatement prepstmt = con.prepareStatement(query);
		return prepstmt;
	}

}
