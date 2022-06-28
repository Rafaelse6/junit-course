package br.com.rafaelsantos.api.resources;

import br.com.rafaelsantos.api.domain.Customer;
import br.com.rafaelsantos.api.domain.dto.CustomerDTO;
import br.com.rafaelsantos.api.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerResourceTest {

    private static final Integer ID      = 1;

    private static final int INDEX = 0;

    private static final String NAME     = "Rafael";

    private static final String EMAIL    = "rafael@email.com";

    private static final String PASSWORD = "123";

    private Customer customer;
    private CustomerDTO customerDTO;

    @InjectMocks
    private CustomerResource resource;

    @Mock
    private CustomerServiceImpl service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCustomer();
    }

    @Test
    void whenFindByIdTheReturnSuccess() {
       when(service.findById(anyInt())).thenReturn(customer);
       when(mapper.map(any(), any())).thenReturn(customerDTO);

       ResponseEntity<CustomerDTO> response = resource.findById(ID);

       assertNotNull(response);
       assertNotNull(response.getBody());
       assertEquals(ResponseEntity.class, response.getClass());
       assertEquals(CustomerDTO.class, response.getBody().getClass());

       assertEquals(ID, response.getBody().getId());
       assertEquals(NAME, response.getBody().getName());
       assertEquals(EMAIL, response.getBody().getEmail());
       assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        when(service.findAll()).thenReturn(List.of(customer));
        when(mapper.map(any(), any())).thenReturn(customerDTO);

        ResponseEntity<List<CustomerDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(CustomerDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
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
    }
}