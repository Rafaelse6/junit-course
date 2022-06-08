package br.com.rafaelsantos.api.services.impl;

import br.com.rafaelsantos.api.domain.Customer;
import br.com.rafaelsantos.api.domain.dto.CustomerDTO;
import br.com.rafaelsantos.api.repositories.CustomerRepository;
import br.com.rafaelsantos.api.services.CustomerService;
import br.com.rafaelsantos.api.services.exceptions.DataIntegrityViolationException;
import br.com.rafaelsantos.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Customer findById(Integer id) {
        Optional<Customer> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public List<Customer> findAll(){
        return repository.findAll();
    }

    @Override
    public Customer create(CustomerDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Customer.class));
    }

    @Override
    public Customer update(CustomerDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Customer.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public void findByEmail(CustomerDTO obj){
        Optional<Customer> customer = repository.findByEmail(obj.getEmail());
        if(customer.isPresent() && !customer.get().getId().equals(obj.getId())){
            throw new DataIntegrityViolationException("Email already in use");
        }
    }
}
