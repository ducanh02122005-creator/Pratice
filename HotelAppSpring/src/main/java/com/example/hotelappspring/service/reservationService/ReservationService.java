package com.example.hotelappspring.service.reservationService;

import com.example.hotelappspring.dtos.ReservationDTO;
import com.example.hotelappspring.entities.Reservation;
import com.example.hotelappspring.mapper.ReservationMapper;
import com.example.hotelappspring.repositories.ReservationRepository;
import com.example.hotelappspring.service.generalService.BasicService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService extends BasicService<Reservation, ReservationDTO,String,Integer> {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    protected JpaRepository<Reservation, Integer> getRepository(){
        return reservationRepository;
    }

    @Override
    protected Reservation toEntity(ReservationDTO dto){
        return reservationMapper.toEntity(dto);
    }

    @Override
    protected ReservationDTO toDto(Reservation entity){
        return reservationMapper.toDto(entity);
    }

    @Override
    protected  void updateRequest(String string,Reservation entity){}

    @Override
    protected Integer getId(Reservation entity){
        return entity.getRoomId();
    }

    @Override
    protected String getPath(){
        return "/reservation/{id}";
    }
}
