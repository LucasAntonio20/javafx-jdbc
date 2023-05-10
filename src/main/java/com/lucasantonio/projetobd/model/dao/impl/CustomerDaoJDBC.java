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
		/*
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM department ORDER BY Name");
			rs = st.executeQuery();

			List<Customer> list = new ArrayList<>();

			while (rs.next()) {
				Customer obj = new Customer(EmployeeID);
				obj.setId(rs.getString("Id"));
				obj.setName(rs.getString("Name"));
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

		 */

		List<Customer> list = new ArrayList<>();

		list.add(new Customer("1", "Celpe", "Pedro", "Pedrin", "Rua 23", "Olinda", "Sul", "80522", "Brasil", "80028922", "357689"));
		list.add(new Customer("2", "Compesa", "Ana", "Aninha", "Rua 67", "Recife", "Norte", "82222", "Brasil", "80028922", "357689"));
		list.add(new Customer("3", "Compesa", "Joao", "ze", "Rua 106", "Olinda", "Norte", "80452", "Brasil", "80028922", "357689"));
		list.add(new Customer("4", "Celpe", "Diego", "didi", "Rua 2", "Paulista", "Norte", "42122", "Brasil", "80028922", "357689"));

		return list;
	}

	@Override
	public void insert(Customer obj) {
		/*
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO department " +
				"(Name) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
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
		}*/
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
