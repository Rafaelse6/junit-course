package br.com.rafaelsantos.api.services.impl;

import br.com.rafaelsantos.api.domain.Customer;
import br.com.rafaelsantos.api.domain.dto.CustomerDTO;
import br.com.rafaelsantos.api.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceImplTest {
    public static final Integer ID      = 1;
    public static final String NAME     = "Rafael";
    public static final String EMAIL    = "rafael@email.com";
    public static final String PASSWORD = "123";
    @InjectMocks
    private CustomerServiceImpl service;

    @Mock
    private CustomerRepository repository;

    @Mock
    private ModelMapper mapper;

    private Customer customer;
    private CustomerDTO customerDTO;
    private Optional<Customer> optionalCustomer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCustomer();
    }

    @Test
    void whenFindByIdThenReturnACustomerInstance() {
       when(repository.findById(anyInt())).thenReturn(optionalCustomer);

        Customer response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Customer.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startCustomer(){
        customer = new Customer(ID, NAME, EMAIL, PASSWORD);
        customerDTO = new CustomerDTO(ID, NAME, EMAIL, PASSWORD);
        optionalCustomer = Optional.of(new Customer(ID, NAME, EMAIL, PASSWORD));
    }
}