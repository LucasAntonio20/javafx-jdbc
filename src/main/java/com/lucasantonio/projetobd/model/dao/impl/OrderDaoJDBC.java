package com.lucasantonio.projetobd.model.dao.impl;

import com.lucasantonio.projetobd.db.DB;
import com.lucasantonio.projetobd.db.DbException;
import com.lucasantonio.projetobd.db.DbIntegrityException;
import com.lucasantonio.projetobd.model.entities.Customer;
import com.lucasantonio.projetobd.model.entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJDBC {

    private Connection conn;

    public OrderDaoJDBC(Connection conn) {
        this.conn = conn;
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
}
