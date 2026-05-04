package com.example.hotelappspring.controller;

import com.example.hotelappspring.dtos.RoomDTO;
import com.example.hotelappspring.mapper.UpdateRoomRequest;
import com.example.hotelappspring.service.roomService.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private RoomService roomService;

    @GetMapping
    public Iterable<RoomDTO> getAllRoom(){
        return roomService.getAllEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Integer id){
        return roomService.getEntityById(id);
    }

    @PostMapping
    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO data, UriComponentsBuilder uriComponentsBuilder){
        return roomService.addEntity(data, uriComponentsBuilder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Integer id, @RequestBody UpdateRoomRequest updateRoomRequest){
        return roomService.updateEntityById(id, updateRoomRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Integer id){
        return roomService.deleteEntityById(id);
    }
}
