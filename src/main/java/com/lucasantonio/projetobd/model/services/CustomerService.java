package com.lucasantonio.projetobd.model.services;

import com.lucasantonio.projetobd.model.dao.CustomerDao;
import com.lucasantonio.projetobd.model.dao.DaoFactory;
import com.lucasantonio.projetobd.model.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private CustomerDao dao = DaoFactory.createDepartmentDao();

    public List<Customer> findAll(){
        return dao.findAll();
    }
}
