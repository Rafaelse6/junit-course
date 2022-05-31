package br.com.rafaelsantos.api.services;

import br.com.rafaelsantos.api.domain.Customer;

public interface CustomerService {

    Customer findById(Integer id);
}
