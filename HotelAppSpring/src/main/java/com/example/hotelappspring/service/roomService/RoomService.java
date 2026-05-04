package com.example.hotelappspring.service.roomService;

import com.example.hotelappspring.dtos.ReservationDTO;
import com.example.hotelappspring.dtos.RoomDTO;
import com.example.hotelappspring.entities.Reservation;
import com.example.hotelappspring.entities.Room;
import com.example.hotelappspring.mapper.RoomMapper;
import com.example.hotelappspring.mapper.UpdateRoomRequest;
import com.example.hotelappspring.repositories.RoomRepository;
import com.example.hotelappspring.service.generalService.BasicService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService extends BasicService<Room, RoomDTO, UpdateRoomRequest,Integer> {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    protected JpaRepository<Room, Integer> getRepository() {
        return roomRepository;
    }

    @Override
    protected Room toEntity(RoomDTO dto) {
        return roomMapper.toEntity(dto);
    }

    @Override
    protected RoomDTO toDto(Room entity) {
        return roomMapper.toDto(entity);
    }

    @Override
    protected void  updateRequest(UpdateRoomRequest request,Room entity){
        roomMapper.update(request,entity);
    }

    @Override
    protected Integer getId(Room entity){
        return entity.getId();
    }

    @Override
    protected String getPath(){
        return "/room/{id}";
    }



}
