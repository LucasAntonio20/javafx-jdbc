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
	public Customer findById(String id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM Customers WHERE CustomerID = ?");
			st.setString(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				return new Customer();
			}
			return null;
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
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
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

			st.setString(1, customer.getCustomerID());
			st.setString(2, customer.getCompanyName());
			st.setString(3, customer.getContactName());
			st.setString(4, customer.getContactTitle());
			st.setString(5, customer.getAddress());
			st.setString(6, customer.getCity());
			st.setString(7, customer.getRegion());
			st.setString(8, customer.getPostalCode());
			st.setString(9, customer.getCountry());
			st.setString(10, customer.getPhone());
			st.setString(11, customer.getFax());


			int rowsAffected = st.executeUpdate();

			if (rowsAffected <= 0) throw new DbException("Unexpected error! No rows affected!");

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Customer customer) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Customers " +
							"SET CompanyName = ? ," +
							"ContactName = ? ," +
							"ContactTitle = ? ," +
							"Address = ? ," +
							"City = ? ," +
							"Region = ? ," +
							"PostalCode = ? ," +
							"Country = ? ," +
							"Phone = ? ," +
							"Fax = ? " +
							"WHERE CustomerID = ?");

			st.setString(1, customer.getCompanyName());
			st.setString(2, customer.getContactName());
			st.setString(3, customer.getContactTitle());
			st.setString(4, customer.getAddress());
			st.setString(5, customer.getCity());
			st.setString(6, customer.getRegion());
			st.setString(7, customer.getPostalCode());
			st.setString(8, customer.getCountry());
			st.setString(9, customer.getPhone());
			st.setString(10, customer.getFax());
			st.setString(11, customer.getCustomerID());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(String id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM Customers WHERE CustomerID = ?");

			st.setString(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
}
