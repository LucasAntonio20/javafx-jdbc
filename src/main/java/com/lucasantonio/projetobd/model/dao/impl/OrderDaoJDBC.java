package com.lucasantonio.projetobd.model.dao.impl;

import com.lucasantonio.projetobd.db.DB;
import com.lucasantonio.projetobd.db.DbException;
import com.lucasantonio.projetobd.model.entities.Customer;
import com.lucasantonio.projetobd.model.entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJDBC {

    private Connection conn;

    public OrderDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public Order findById(int id) {

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Orders WHERE OrderID = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return new Order();
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

    public List<Order> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Orders ORDER BY OrderID");
            rs = st.executeQuery();

            List<Order> list = new ArrayList<>();

            while (rs.next()) {
                Order obj = new Order();
                obj.setOrderID(rs.getInt("OrderID"));
                obj.setCustomerID(rs.getString("CustomerID"));
                obj.setEmployeeID(rs.getInt("EmployeeID"));
                obj.setOrderDate(rs.getDate("OrderDate"));
                obj.setRequiredDate(rs.getDate("RequiredDate"));
                obj.setShippedDate(rs.getDate("ShippedDate"));
                obj.setShipVia(rs.getInt("ShipVia"));
                obj.setFreight(rs.getDouble("Freight"));
                obj.setShipName(rs.getString("ShipName"));
                obj.setShipCity(rs.getString("ShipCity"));
                obj.setShipRegion(rs.getString("ShipRegion"));
                obj.setShipPostalCode(rs.getString("ShipPostalCode"));
                obj.setShipCountry(rs.getString("ShipCountry"));
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

    public void insert(Order order) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO Orders (CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            st.setString(1, order.getCustomerID());
            st.setInt(2, order.getEmployeeID());
            st.setTimestamp(3, new Timestamp(order.getOrderDate().getTime()));
            st.setTimestamp(4, new Timestamp(order.getRequiredDate().getTime()));
            st.setTimestamp(5, new Timestamp(order.getShippedDate().getTime()));
            st.setInt(6, order.getShipVia());
            st.setDouble(7, order.getFreight());
            st.setString(8, order.getShipName());
            st.setString(9, order.getShipAddress());
            st.setString(10, order.getShipCity());
            st.setString(11, order.getShipRegion());
            st.setString(12, order.getShipPostalCode());
            st.setString(13, order.getShipCountry());


            int rowsAffected = st.executeUpdate();

            if (rowsAffected <= 0) throw new DbException("Unexpected error! No rows affected!");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}
