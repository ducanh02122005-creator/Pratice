package com.example.hotelappspring.mapper;

import com.example.hotelappspring.dtos.CustomerDTO;
import com.example.hotelappspring.entities.Customer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    CustomerDTO toDto(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateCustomerRequest request, @MappingTarget Customer customer);
}
