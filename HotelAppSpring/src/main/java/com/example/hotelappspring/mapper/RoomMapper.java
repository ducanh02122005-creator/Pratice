package com.example.hotelappspring.mapper;

import com.example.hotelappspring.dtos.RoomDTO;
import com.example.hotelappspring.entities.Room;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(source = "roomNumber",target = "roomNumber")
    @Mapping(source = "roomPrice",target = "roomPrice")
    @Mapping(source = "roomType",target = "roomType")
    RoomDTO toDto(Room room);
    Room toEntity(RoomDTO roomDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateRoomRequest request, @MappingTarget Room room);
}
