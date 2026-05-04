package com.example.hotelappspring.mapper;

import lombok.Data;

@Data
public class UpdateRoomRequest {
    private Integer roomNumber;
    private Float roomPrice;
    private String roomType;
}
