package br.com.rafaelsantos.api.resources;

import br.com.rafaelsantos.api.domain.dto.CustomerDTO;
import br.com.rafaelsantos.api.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( value = "/customer")
public class CustomerResource {

    private static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CustomerService service;

    @GetMapping(value = ID)
    public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), CustomerDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll()
                .stream().map(x -> mapper.map(x, CustomerDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO obj){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID).buildAndExpand(service.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @RequestBody CustomerDTO obj){
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(obj), CustomerDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<CustomerDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
