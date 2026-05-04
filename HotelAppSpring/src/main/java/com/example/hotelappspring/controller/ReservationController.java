package com.example.hotelappspring.controller;

import com.example.hotelappspring.dtos.ReservationDTO;
import com.example.hotelappspring.service.reservationService.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;

    @GetMapping
    public Iterable<ReservationDTO> getAllReservation(){
        return reservationService.getAllEntity();
    }
}
