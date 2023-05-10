package com.lucasantonio.projetobd.models.services;

import com.lucasantonio.projetobd.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public List<Customer> findAll(){
        List<Customer> list = new ArrayList<>();

        list.add(new Customer("1", "Celpe", "Pedro", "Pedrin", "Rua 23", "Olinda", "Sul", "80522", "Brasil", "80028922", "357689"));
        list.add(new Customer("2", "Compesa", "Ana", "Aninha", "Rua 67", "Recife", "Norte", "82222", "Brasil", "80028922", "357689"));
        list.add(new Customer("3", "Compesa", "Joao", "ze", "Rua 106", "Olinda", "Norte", "80452", "Brasil", "80028922", "357689"));
        list.add(new Customer("4", "Celpe", "Diego", "didi", "Rua 2", "Paulista", "Norte", "42122", "Brasil", "80028922", "357689"));

        return list;
    }
}
