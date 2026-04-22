package com.example.hotelappspring.repositories;

import com.example.hotelappspring.entities.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepository extends JpaRepository<customer,Integer> {

}
