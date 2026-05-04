package com.example.hotelappspring.controller;

import com.example.hotelappspring.dtos.CustomerDTO;
import com.example.hotelappspring.mapper.CustomerMapper;
import com.example.hotelappspring.mapper.UpdateCustomerRequest;
import com.example.hotelappspring.repositories.CustomerRepository;
import com.example.hotelappspring.service.userService.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CustomerService customerService;

    @GetMapping
    public Iterable<CustomerDTO> getAllCustomer(){
        return customerService.getAllEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Integer id){return customerService.getEntityById(id);}

    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO data, UriComponentsBuilder uriComponentsBuilder){return customerService.addEntity(data,uriComponentsBuilder);}

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @RequestBody UpdateCustomerRequest updateRequest){return customerService.updateEntityById(id,updateRequest);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id){return customerService.deleteEntityById(id);}
}
