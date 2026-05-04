package com.example.hotelappspring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    dùng indentity để match với id đã tạo
    private Integer id;

    @Column(nullable = false, name = "first_Name")
    private String firstName;

    @Column(nullable = false, name = "last_Name")
    private String lastName;

    @Column(nullable = false, name = "email")
    private String email;
}
