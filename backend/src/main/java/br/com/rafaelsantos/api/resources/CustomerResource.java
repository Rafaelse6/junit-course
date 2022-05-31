package br.com.rafaelsantos.api.resources;

import br.com.rafaelsantos.api.domain.Customer;
import br.com.rafaelsantos.api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/customer")
public class CustomerResource {
    @Autowired
    private CustomerService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
