package com.example.hotelappspring.controller;

import com.example.hotelappspring.dtos.customerDTO;
import com.example.hotelappspring.mapper.customerMapper;
import com.example.hotelappspring.mapper.updateCustomerRequest;
import com.example.hotelappspring.repositories.customerRepository;
import com.example.hotelappspring.service.users.customerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class customerController {
    private customerRepository customerRepository;
    private customerMapper customerMapper;
    private customerService customerService;

    @GetMapping
    public Iterable<customerDTO> getAllCustomer(){
        return customerService.getAllEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<customerDTO> getCustomer(@PathVariable Integer id){return customerService.getEntityById(id);}

    @PostMapping
    public ResponseEntity<customerDTO> addCustomer(@RequestBody customerDTO data, UriComponentsBuilder uriComponentsBuilder){return customerService.addEntity(data,uriComponentsBuilder);}

    @PutMapping("/{id}")
    public ResponseEntity<customerDTO> updateCustomer(@PathVariable Integer id, @RequestBody updateCustomerRequest updateRequest){return customerService.updateEntityById(id,updateRequest);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id){return customerService.deleteEntityById(id);}
}
