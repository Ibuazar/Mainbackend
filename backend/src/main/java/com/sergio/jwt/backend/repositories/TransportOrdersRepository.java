package com.sergio.jwt.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergio.jwt.backend.entites.TransportOrders;

public interface TransportOrdersRepository extends JpaRepository<TransportOrders,Integer>{
    
}
