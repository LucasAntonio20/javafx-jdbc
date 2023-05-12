package com.lucasantonio.projetobd.model.dao;

import java.util.List;

import com.lucasantonio.projetobd.model.entities.Customer;

public interface CustomerDao {

	void insert(Customer obj);
	void update(Customer obj);
	void deleteById(String id);
	Customer findById(String id);
	List<Customer> findAll();
	List<Customer> getByCountry(String country);
}
