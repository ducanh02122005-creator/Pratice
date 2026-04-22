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
public class customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    dùng indentity để match với id đã tạo
    private Integer id;

    @Column(nullable = false, name = "firstName")
    private String first_Name;

    @Column(nullable = false, name = "lastName")
    private String last_Name;

    @Column(nullable = false, name = "email")
    private String email;
}
