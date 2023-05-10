package com.lucasantonio.projetobd.model.dao;

import com.lucasantonio.projetobd.db.DB;
import com.lucasantonio.projetobd.model.dao.impl.CustomerDaoJDBC;
public class DaoFactory {
	public static CustomerDao createCustomerDao() {
		return new CustomerDaoJDBC(DB.getConnection());
	}
}
