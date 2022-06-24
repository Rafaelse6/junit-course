package br.com.rafaelsantos.api.services.impl;

import br.com.rafaelsantos.api.domain.Customer;
import br.com.rafaelsantos.api.domain.dto.CustomerDTO;
import br.com.rafaelsantos.api.repositories.CustomerRepository;
import br.com.rafaelsantos.api.services.exceptions.DataIntegrityViolationException;
import br.com.rafaelsantos.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceImplTest {
    private static final Integer ID      = 1;
    private static final String NAME     = "Rafael";
    private static final String EMAIL    = "rafael@email.com";
    private static final String PASSWORD = "123";
    private static final int INDEX = 0;
    public static final String EMAIL_ALREADY_IN_USE = "Email already in use";
    public static final String OBJECT_NOT_FOUND = "Object not found";
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
    void whenFindByIdThenReturnObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            service.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(customer));
        List<Customer> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(customer.getClass(), response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
       when(repository.save(any())).thenReturn(customer);

       Customer response = service.create(customerDTO);

       assertNotNull(response);
       assertEquals(Customer.class, response.getClass());
       assertEquals(ID, response.getId());
       assertEquals(NAME, response.getName());
       assertEquals(EMAIL, response.getEmail());
       assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnADataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalCustomer);

        try {
            optionalCustomer.get().setId(2);
            service.create(customerDTO);
        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_IN_USE, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(customer);

        Customer response = service.update(customerDTO);

        assertNotNull(response);
        assertEquals(Customer.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnADataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalCustomer);

        try {
            optionalCustomer.get().setId(2);
            service.create(customerDTO);
        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_IN_USE, ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalCustomer);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);

        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException(){
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));
        try {
            service.delete(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }
    }

    private void startCustomer(){
        customer = new Customer(ID, NAME, EMAIL, PASSWORD);
        customerDTO = new CustomerDTO(ID, NAME, EMAIL, PASSWORD);
        optionalCustomer = Optional.of(new Customer(ID, NAME, EMAIL, PASSWORD));
    }
}