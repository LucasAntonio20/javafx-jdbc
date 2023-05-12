package com.lucasantonio.projetobd.model.services;

import com.lucasantonio.projetobd.db.DB;
import com.lucasantonio.projetobd.model.dao.CustomerDao;
import com.lucasantonio.projetobd.model.dao.DaoFactory;
import com.lucasantonio.projetobd.model.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private CustomerDao dao = DaoFactory.createCustomerDao();

    public List<Customer> findAll(){
        return dao.findAll();
    }

    public void saveOrUpdate(Customer obj){
        if (dao.findById(obj.getCustomerID()) == null) dao.insert(obj);
        else dao.update(obj);
    }

    public void remove(Customer obj) {
        dao.deleteById(obj.getCustomerID());
    }

    public List<Customer> getByCountry(String country) {
        return dao.getByCountry(country);
    }
}
