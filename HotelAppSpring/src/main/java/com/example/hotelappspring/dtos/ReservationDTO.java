package com.example.hotelappspring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Integer roomNumber;
    private Date checkInDate;
    private Date checkOutDate;

}
