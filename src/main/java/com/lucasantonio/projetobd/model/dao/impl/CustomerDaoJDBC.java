package com.lucasantonio.projetobd.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lucasantonio.projetobd.model.dao.CustomerDao;
import com.lucasantonio.projetobd.model.entities.Customer;
import com.lucasantonio.projetobd.db.*;

public class CustomerDaoJDBC implements CustomerDao {

	private Connection conn;
	
	public CustomerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Customer findById(Integer id) {

		/*
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM department WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Customer obj = new Customer();
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}*/
		return new Customer();
	}

	@Override
	public List<Customer> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM Customers ORDER BY CustomerID");
			rs = st.executeQuery();

			List<Customer> list = new ArrayList<>();

			while (rs.next()) {
				Customer obj = new Customer();
				obj.setCustomerID(rs.getString("CustomerID"));
				obj.setCompanyName(rs.getString("CompanyName"));
				obj.setContactName(rs.getString("ContactName"));
				obj.setContactTitle(rs.getString("ContactTitle"));
				obj.setAddress(rs.getString("Address"));
				obj.setCity(rs.getString("City"));
				obj.setRegion(rs.getString("Region"));
				obj.setPostalCode(rs.getString("PostalCode"));
				obj.setCountry(rs.getString("Country"));
				obj.setPhone(rs.getString("Phone"));
				obj.setFax(rs.getString("Fax"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Customer customer) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
            "INSERT INTO Customers (CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			st.setString(1, "ALFKI");
   			st.setString(2, "Alfreds Futterkiste");
    		st.setString(3, "Maria Anders");
    		st.setString(4, "Sales Representative");
    		st.setString(5, "Obere Str. 57");
    		st.setString(6, "Berlin");
    		st.setString(7, null);
    		st.setString(8, "12209");
    		st.setString(9, "Germany");
    		st.setString(10, "030-0074321");
    		st.setString(11, "030-0076545");


			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					customer.setCustomerID(String.valueOf(id));
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Customer obj) {
		/*
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE department " +
				"SET Name = ? " +
				"WHERE Id = ?");

			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}*/
	}

	@Override
	public void deleteById(Integer id) {
		/*
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM department WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
		*/
	}
}
