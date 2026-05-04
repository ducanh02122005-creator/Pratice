package com.example.hotelappspring.service.userService;

import com.example.hotelappspring.dtos.CustomerDTO;
import com.example.hotelappspring.entities.Customer;
import com.example.hotelappspring.mapper.CustomerMapper;
import com.example.hotelappspring.mapper.UpdateCustomerRequest;
import com.example.hotelappspring.repositories.CustomerRepository;
import com.example.hotelappspring.service.generalService.BasicService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService extends BasicService<Customer, CustomerDTO, UpdateCustomerRequest, Integer> {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    protected JpaRepository<Customer, Integer> getRepository(){
        return customerRepository;
    }
    @Override
    protected Customer toEntity(CustomerDTO dto){
        return customerMapper.toEntity(dto);
    }
    @Override
    protected CustomerDTO toDto(Customer entity){
        return customerMapper.toDto(entity);
    }
    @Override
    protected void updateRequest(UpdateCustomerRequest request, Customer entity) {
        customerMapper.update(request, entity);
    }
    @Override
    protected Integer getId(Customer entity){
        return entity.getId();
    };
    @Override
    protected String getPath(){
        return "/customer/{id}";
    };


}
