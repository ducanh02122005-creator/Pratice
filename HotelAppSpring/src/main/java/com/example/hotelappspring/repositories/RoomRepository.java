package com.example.hotelappspring.repositories;

import com.example.hotelappspring.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {
}
