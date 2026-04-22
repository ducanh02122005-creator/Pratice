package com.example.hotelappspring.mapper;

import com.example.hotelappspring.dtos.customerDTO;
import com.example.hotelappspring.entities.customer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface customerMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "first_Name", target = "first_Name")
    @Mapping(source = "last_Name", target = "last_Name")
    customerDTO toDto(customer customer);
    customer toEntity(customerDTO customerDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(updateCustomerRequest request,@MappingTarget customer customer);
}
