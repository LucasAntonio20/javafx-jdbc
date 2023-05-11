package com.lucasantonio.projetobd.model.services;

import com.lucasantonio.projetobd.db.DB;
import com.lucasantonio.projetobd.model.dao.impl.OrderDaoJDBC;
import com.lucasantonio.projetobd.model.entities.Order;

import java.util.List;

public class OrderService {

    private OrderDaoJDBC dao = new OrderDaoJDBC(DB.getConnection());

    public List<Order> findAll(){
        return dao.findAll();
    }
}
