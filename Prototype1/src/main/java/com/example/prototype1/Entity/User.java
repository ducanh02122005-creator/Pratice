package com.example.prototype1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
//    id and generateValue help automatically generate primary key
    private int id;
    @Column(name = "name") //column help automatically mapping column value in mysql and entity value
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
