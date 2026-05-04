package com.example.hotelappspring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "rooms")
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "room_Number")
    private Integer roomNumber;

    @Column(name = "room_Price")
    private Float roomPrice;

    @Column(name = "room_Type")
    private String roomType;

    @Column(name = "is_Free")
    private Boolean isFree;
}
