package com.example.hotelappspring.mapper;

import lombok.Data;

@Data
public class UpdateCustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
}
