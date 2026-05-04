package com.example.hotelappspring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "reservation")
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @Column(name = "room_Id")
    private Integer roomId;

    @Column(name = "room_Number")
    private Integer roomNumber;

    @Column(name = "check_In_Date")
    private Date checkInDate;

    @Column(name = "check_Out_Date")
    private Date checkOutDate;

    @Column(name = "customer_Id")
    private Integer customerId;


}
