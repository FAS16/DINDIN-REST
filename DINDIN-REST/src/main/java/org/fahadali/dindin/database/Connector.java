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

	/*
	 * used to create a result set of a preparedstatement to be executed in the
	 * database.
	 * 
	 * @param query is the result set of the preparedstatement to be executed.
	 * 
	 * @return rs is the result set executed from the preparedstatement.
	 * 
	 * @throws SQLException if no connection can be made.
	 */
	public ResultSet doQuery(String query) throws SQLException {
		PreparedStatement prepstmt = con.prepareStatement(query);
		ResultSet rs = prepstmt.executeQuery();
		return rs;
	}

	/*
	 * used to create an updating statement.
	 * 
	 * @param query is the updating statement.
	 * 
	 * @throws SQLException if no connection can be made.
	 */
	public void doUpdate(String query) throws SQLException {
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
	}

	/*
	 * used to create a prepared statement.
	 * 
	 * @param query is the prepared statement to create.
	 * 
	 * @return prepstmt is the prepared statement created.
	 * 
	 * @throws SQLException if no connection can be made.
	 */
	public PreparedStatement doPreparedStmt(String query) throws SQLException {
		PreparedStatement prepstmt = con.prepareStatement(query);
		return prepstmt;
	}

}
