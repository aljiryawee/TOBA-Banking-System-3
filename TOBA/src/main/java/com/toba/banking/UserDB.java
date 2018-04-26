package com.toba.banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDB {

	public void insert(User user) {
		Context initContext;
		Context envContext = null;
		DataSource ds = null;
		try {
			initContext = new InitialContext();
			envContext = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup("jdbc/UsersDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		Connection conn;
		try {
			conn = ds.getConnection();

			String sql = "INSERT INTO public.user(username, password, firstname, lastname, phone, address, city, state, zipcode, email)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getPhone());
			preparedStatement.setString(6, user.getAddress());
			preparedStatement.setString(7, user.getCity());
			preparedStatement.setString(8, user.getState());
			preparedStatement.setString(9, user.getZipcode());
			preparedStatement.setString(10, user.getEmail());
			
			System.out.println(user.toString());
			
			int result = preparedStatement.executeUpdate();
			
			if(result==1)
				System.out.println("User inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
