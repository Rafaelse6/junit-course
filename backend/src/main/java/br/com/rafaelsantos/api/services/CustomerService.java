package br.com.rafaelsantos.api.services;

import br.com.rafaelsantos.api.domain.Customer;

import java.util.List;

public interface CustomerService {

    Customer findById(Integer id);
    List<Customer> findAll();
}
