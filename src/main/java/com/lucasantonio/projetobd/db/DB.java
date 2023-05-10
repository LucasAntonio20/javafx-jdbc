package com.lucasantonio.projetobd.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	//DESKTOP-959S46T\SQLEXPRESS:
	//jdbc:sqlserver://DESKTOP-959S46T\SQLEXPRESS;encrypt=true;databaseName=Northwind;user=admin;password=1234;
	private static final String URL = "jdbc:sqlserver://DESKTOP-959S46T\\SQLEXPRESS:1433;encrypt=false;databaseName=Northwind";
	private static final String USER = "admin";
	private static final String PASSWORD = "123";

	private static Connection conn = null;


	public static void connect(){
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexão estabelecida com sucesso.");
		} catch (SQLException e) {
			System.out.println("Falha ao estabelecer conexão.");
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Conexão fechada com sucesso.");
			}
		} catch (SQLException e) {
			System.out.println("Falha ao fechar conexão.");
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
