package com.sergio.jwt.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sergio.jwt.backend.entites.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

    @Query("SELECT v FROM Vehicle v WHERE v.name IN ('Traveller','Bus')")
    List<Vehicle> findTravelsVehicles();
    
    @Query("SELECT v FROM Vehicle v WHERE v.name IN ('Pickup Truck','Heavy Load')")
    List<Vehicle> findTransportVehicles();
    
     @Query("SELECT v FROM Vehicle v WHERE v.name = 'Traveller' ")
    List<Vehicle> findTravellerVehicles();
     
    @Query("SELECT v FROM Vehicle v WHERE v.name = 'Bus' ")
    List<Vehicle> findBusVehicles();
     
    @Query("SELECT v FROM Vehicle v WHERE v.name = 'PickUp Truck' ")
    List<Vehicle> findPickUpTruckVehicles();
     
    @Query("SELECT v FROM Vehicle v WHERE v.name = 'Heavy Load' ")
    List<Vehicle> findHeavyLoadVehicles();
     
    
}
