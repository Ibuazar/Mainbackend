package com.sergio.jwt.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergio.jwt.backend.entites.Vehicle;
import com.sergio.jwt.backend.repositories.VehicleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vehicleRepository;

    public void addVehicle(Vehicle newVehicle){
            vehicleRepository.save(newVehicle);
    }
    public List<Vehicle> getAllVehicls(){
        return vehicleRepository.findAll();
    }

        public List<Vehicle> getAllProducts() {
            return vehicleRepository.findAll();
        }

        // public byte[] getProductImage(int vehicleId) {
        //     Optional<Vehicle> productOptional = vehicleRepository.findById(vehicleId);
        //     if (productOptional.isPresent()) {
        //         System.out.println(productOptional.get().getImage());
        //         return productOptional.get().getImage();
        //     }

        //     return null;
        // }
}
