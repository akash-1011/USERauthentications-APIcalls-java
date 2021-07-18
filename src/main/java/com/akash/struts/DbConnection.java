package com.akash.struts;

import java.sql.*;
import java.util.Arrays;

public class DbConnection {

	static Connection conn = null;
	static String databaseName = "";
	static String url = "jdbc:mysql://localhost:3306/" + databaseName;
	static String username = "root";
	static String password = "akash112001";

	public static int addRecord(String ps1, String ps2)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);

		java.util.Date date = new java.util.Date();
		Timestamp sqlDate = new Timestamp(date.getTime());

		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO `strutsdb`.`user` (`username`,`password`,`registerTime`) VALUES ('" + ps1
						+ "','" + ps2 + "',?);");
		ps.setTimestamp(1, sqlDate);

		int status = ps.executeUpdate();

		return status;
	}

	public static ResultSet checkApprove(String ps1)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);

		PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM `strutsdb`.`user` WHERE `username` ='" + ps1 + "' AND `approved` = 1;");
		ResultSet status = ps.executeQuery();

		return status;
	}

	public static ResultSet getProfile(String ps1) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);
		String stmt = "select a.userId,a.username,a.approved,a.registerTime,a.approvedTime,a.levelID,b.lName from strutsdb.user as a, strutsdb.level as b where a.levelID = b.levelID and a.username = '"
				+ ps1 + "';";
		PreparedStatement ps = conn.prepareStatement(stmt);

		ResultSet status = ps.executeQuery();

		return status;
	}

	public static String getLevelName(String ps1) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);
		String stmt = "select b.lName from strutsdb.user as a, strutsdb.level as b where a.username = '" + ps1
				+ "' and a.levelID = b.levelID;";
		PreparedStatement ps = conn.prepareStatement(stmt);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getString("lName");
		} else {
			return "";
		}
	}

	public static ResultSet checkRecord(String ps1, String ps2) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);
		PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM `strutsdb`.`user` WHERE `username` ='" + ps1 + "' AND `password` ='" + ps2 + "';");

		ResultSet status = ps.executeQuery();

		return status;
	}

	public static ResultSet getRequest() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);
		String stmt = "select a.userId,a.username,a.approved,a.registerTime,a.approvedTime,a.levelID, b.lName from strutsdb.user as a, strutsdb.level as b where a.levelID = b.levelID and a.approved = 0;";
		PreparedStatement ps = conn.prepareStatement(stmt);

		ResultSet status = ps.executeQuery();

		return status;
	}

	public static ResultSet getLevelOption() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `strutsdb`.`level`;");

		ResultSet status = ps.executeQuery();

		return status;
	}

	public static int setApprove(int id, int levelId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);

		java.util.Date date = new java.util.Date();
		Timestamp sqlDate = new Timestamp(date.getTime());

		PreparedStatement ps = conn.prepareStatement(
				"UPDATE `strutsdb`.`user` SET `approved` = 1, `levelID` = ? ,`approvedTime` = ?  WHERE `userId` = " + id
						+ ";");
		ps.setInt(1, levelId);
		ps.setTimestamp(2, sqlDate);

		int status = ps.executeUpdate();

		return status;
	}

	public static ResultSet getApproved()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);

		String stmt = "select a.userId,a.username,a.approved,a.approvedTime,b.lName from strutsdb.user as a, strutsdb.level as b where a.levelID = b.levelID and a.approved = 1;";

		PreparedStatement ps = conn.prepareStatement(stmt);
		ResultSet status = ps.executeQuery();

		return status;
	}

	public static ResultSet getUsersAdmin()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);

		String stmt = "select a.userId,a.username,a.approved,a.approvedTime,a.registerTime,b.lName from strutsdb.user as a, strutsdb.level as b where a.levelID = b.levelID;";

		PreparedStatement ps = conn.prepareStatement(stmt);
		ResultSet status = ps.executeQuery();

		return status;
	}

	public static ResultSet getUsers(String ps1)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);

		PreparedStatement ps = conn.prepareStatement(
				"select a.userId,a.username,a.approved,a.approvedTime,a.registerTime,b.lName from strutsdb.user as a, strutsdb.level as b where a.levelID = b.levelID and a.username != ?;");
		ps.setString(1, ps1);

		ResultSet status = ps.executeQuery();

		return status;
	}

	public static int checkTime(String ps1) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);

		String stmt = "select username from strutsdb.time where username = '" + ps1 + "';";

		PreparedStatement ps = conn.prepareStatement(stmt);
		ResultSet status = ps.executeQuery();

		if (status.next()) {
			return 1;
		} else {
			return 0;
		}
	}

	/*
	 * public static void addUserTime(String ps1, int ps2, int ps3) throws
	 * ClassNotFoundException, SQLException {
	 * Class.forName("com.mysql.cj.jdbc.Driver"); conn =
	 * DriverManager.getConnection(url,username,password);
	 * 
	 * PreparedStatement ps = conn.
	 * prepareStatement("INSERT INTO `strutsdb`.`timerange` (`username`,`day`,`allowedTime`) VALUES ('"
	 * +ps1+"','"+ps2+"','"+ps3+"');");
	 * 
	 * ps.executeUpdate();
	 * 
	 * conn.close(); }
	 */

	public static void addTime(String ps1, String ps2, String collection) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);

		PreparedStatement s = conn
				.prepareStatement("INSERT INTO `strutsdb`.`time` (`username`) VALUES ('" + ps1 + "');");
		s.executeUpdate();

		PreparedStatement ps = conn.prepareStatement("update strutsdb.time set " + ps2 + " = ? where username= ?;");
		ps.setString(1, collection);
		ps.setString(2, ps1);
		ps.executeUpdate();

		conn.close();
	}

	public static String checkUserTime(String ps1, String day) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);

		PreparedStatement ps = conn.prepareStatement("select " + day + " from strutsdb.time where username = ?;");

		ps.setString(1, ps1);

		ResultSet status = ps.executeQuery();

		if (status.next()) {
			return status.getString(1);
		} else {
			return null;
		}

	}

	public static ResultSet getTabs(String lName) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);

		PreparedStatement ps;

		if (lName.length() > 1) {
			ps = conn.prepareStatement("select tabName,tabLink from strutsdb.tabs where `" + lName + "` = 1;");
		} else {
			ps = conn.prepareStatement("select tabName,tabLink from strutsdb.tabs;");
		}

		return ps.executeQuery();

	}

	public static void close() throws SQLException {
		conn.close();
	}

}
