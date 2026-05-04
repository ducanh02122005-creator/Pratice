package com.example.hotelappspring.repositories;

import com.example.hotelappspring.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
}
