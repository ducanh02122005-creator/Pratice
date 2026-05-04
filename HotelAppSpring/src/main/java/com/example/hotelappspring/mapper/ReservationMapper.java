package com.example.hotelappspring.mapper;

import com.example.hotelappspring.dtos.ReservationDTO;
import com.example.hotelappspring.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mapping(source = "roomNumber", target = "roomNumber")
    @Mapping(source = "checkInDate", target = "checkInDate")
    @Mapping(source = "checkOutDate", target = "checkOutDate")
    ReservationDTO toDto(Reservation reservation);
    Reservation toEntity(ReservationDTO reservationDTO);

}
