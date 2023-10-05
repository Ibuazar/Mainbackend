package com.sergio.jwt.backend.entites;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "travels_orders")
public class Order {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


     @Column(name = "vehicle_name", nullable = false)
    @Size(max = 100)
    private String vehicleName;

     @Column(name = "customer_email", nullable = false)
    private String email;
    
     @Column(name = "check_in_date", nullable = false)
    private String checkInDate;
    
     @Column(name = "check_out_date", nullable = false)
    private String checkOutDate;
    
     @Column(name = "children", nullable = false)
    private String children;

    
    @Column(name = "adult", nullable = false)
    private String adult;
    
    
    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "phone", nullable = false)
    private String phone;
}
