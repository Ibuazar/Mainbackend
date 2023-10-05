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
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


     @Column(name = "name", nullable = false)
    @Size(max = 100)
    private String name;

     @Column(name = "number", nullable = false)
    @Size(max = 100)
    private String number;

    @Column(name = "model", nullable = false)
    @Size(max = 100)
    private String model;

    @Column(name = "fuel_type", nullable = false)
    @Size(max = 100)
    private String fuelType;

    @Column(name = "seats", nullable = false)
    @Size(max = 10)
    private String seats;

    @Column(name = "rent", nullable = false)
    @Size(max = 10)
    private String rent;

    @Column(name = "driver_rent", nullable = false)
    @Size(max = 10)
    private String driverRent;

   
    @Column(length = 2048576)
    private String image;


    public void getImage(String originalFilename) {
    }

    
}
