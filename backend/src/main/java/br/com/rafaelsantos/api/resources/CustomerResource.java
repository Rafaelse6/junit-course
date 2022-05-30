package br.com.rafaelsantos.api.resources;

import br.com.rafaelsantos.api.domain.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/customer")
public class CustomerResource {

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(new Customer(1, "Rafael", "email@email.com", "123"));
    }
}
