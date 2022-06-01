package br.com.rafaelsantos.api.config;

import br.com.rafaelsantos.api.domain.Customer;
import br.com.rafaelsantos.api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private CustomerRepository repository;

    @Bean
    public void startDB(){
        Customer c1 =  new Customer(null, "Rafael", "rafael@email.com", "123");
        Customer c2 =  new Customer(null, "Ana", "ana@email.com", "123");

        repository.saveAll(List.of(c1,c2));
    }
}
