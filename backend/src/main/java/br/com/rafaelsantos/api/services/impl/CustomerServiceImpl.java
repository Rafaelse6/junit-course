package br.com.rafaelsantos.api.services.impl;

import br.com.rafaelsantos.api.domain.Customer;
import br.com.rafaelsantos.api.repositories.CustomerRepository;
import br.com.rafaelsantos.api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer findById(Integer id) {
        Optional<Customer> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
