package com.example.hotelappspring.service.users;

import com.example.hotelappspring.dtos.customerDTO;
import com.example.hotelappspring.entities.customer;
import com.example.hotelappspring.mapper.customerMapper;
import com.example.hotelappspring.mapper.updateCustomerRequest;
import com.example.hotelappspring.repositories.customerRepository;
import com.example.hotelappspring.service.generalService.basicService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class customerService extends basicService<customer, customerDTO, updateCustomerRequest, Integer> {
    private final customerRepository customerRepository;
    private final customerMapper customerMapper;
    @Override
    protected JpaRepository<customer, Integer> getRepository(){
        return customerRepository;
    }
    @Override
    protected customer toEntity(customerDTO dto){
        return customerMapper.toEntity(dto);
    }
    @Override
    protected customerDTO toDto(customer entity){
        return customerMapper.toDto(entity);
    }
    @Override
    protected void updateRequest(updateCustomerRequest request, customer entity) {
        customerMapper.update(request, entity);
    }
    @Override
    protected Integer getId(customer entity){
        return entity.getId();
    };
    @Override
    protected String getPath(){
        return "/customer/{id}";
    };


}
