package com.sergio.jwt.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergio.jwt.backend.entites.Order;
import com.sergio.jwt.backend.repositories.OrderRepository;

@Service
public class OrderService {
    
      @Autowired
    OrderRepository orderRepository;


    public void bookVehicle(Order order){
        orderRepository.save(order);
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll() ;
    }
}
