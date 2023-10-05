package com.sergio.jwt.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sergio.jwt.backend.entites.User;
import com.sergio.jwt.backend.entites.Vehicle;
import com.sergio.jwt.backend.repositories.UserRepository;
import com.sergio.jwt.backend.repositories.VehicleRepository;
import com.sergio.jwt.backend.services.FileStorageService;
import com.sergio.jwt.backend.services.VehicleService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AdminController {


    @Autowired
    UserRepository userRepository;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    VehicleRepository vehicleRepository;
     @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/users")
    public List<User> messages() {
        return userRepository.findAll();
    }

    
    
      @GetMapping("/getVehicles/{id}")
    public Optional<Vehicle> getVehiclesById(@PathVariable int id){
        return vehicleRepository.findById(id);
    }

     @GetMapping("/image/{imageName}")
     public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        Resource resource = fileStorageService.loadFile(imageName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

//     @GetMapping("/image/{vehicleId}")
//      public ResponseEntity<byte[]> getProductImage(@PathVariable int vehicleId) {
//         System.out.println("yess");
//     byte[] imageBytes = vehicleService.getProductImage(vehicleId);
//     HttpHeaders headers = new HttpHeaders();
//     headers.setContentType(MediaType.IMAGE_JPEG); // Adjust content type based on your image format
//     return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
// }

    @DeleteMapping("/deleteUser/{email}")
    public String deleteUser(@PathVariable String email){

        User user = userRepository.findByLogin(email).get();
        fileStorageService.deleteFile(user.getImage());
        Long id = (Long)user.getId();
         userRepository.deleteById(id);

         return "User deleted Successfully!";
    }

     @DeleteMapping("/deleteVehicle/{id}")
    public String deleteVehicle(@PathVariable int id){

        Vehicle vehicle =  vehicleRepository.findById(id).get();
        fileStorageService.deleteFile(vehicle.getImage());
       vehicleRepository.deleteById(id);

         return "Vehicle deleted Successfully!";
    }

    @GetMapping("/getUserRole/{email}")
    public String getRole(@PathVariable String email){

        User user = userRepository.findByLogin(email).get();
        String role = user.getRole();

         return role.toString();
    }


    @PostMapping("/addVehicle")
    public ResponseEntity<String> addVehicle(@RequestParam("name") String name,
            @RequestParam("number") String number,
            @RequestParam("model") String model,
            @RequestParam("fuelType") String fuelType,
            @RequestParam("seats") String seats,
            @RequestParam("rent") String rent,
            @RequestParam("driverRent") String driverRent,
            @RequestParam("image") MultipartFile image){

                try{
                    Vehicle vehicle = new Vehicle();
                    vehicle.setName(name);
                    vehicle.setModel(model);
                    vehicle.setNumber(number);
                    vehicle.setFuelType(fuelType);
                    vehicle.setSeats(seats);
                    vehicle.setRent(rent);
                    vehicle.setDriverRent(driverRent);
                    vehicle.setImage(image.getOriginalFilename());

                    fileStorageService.storeFile(image);
                    vehicleService.addVehicle(vehicle);
                return ResponseEntity.ok("vehicle added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding vehicle");
        }

    }

     @PutMapping("/updateVehicle/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable int id,
            @RequestParam("name") String name,
            @RequestParam("number") String number,
            @RequestParam("model") String model,
            @RequestParam("fuelType") String fuelType,
            @RequestParam("seats") String seats,
            @RequestParam("rent") String rent,
            @RequestParam("driverRent") String driverRent,
            @RequestParam("image") MultipartFile image){

                try{
                    Vehicle vehicle = new Vehicle();
                    vehicle.setId(id);
                    vehicle.setName(name);
                    vehicle.setModel(model);
                    vehicle.setNumber(number);
                    vehicle.setFuelType(fuelType);
                    vehicle.setSeats(seats);
                    vehicle.setRent(rent);
                    vehicle.setDriverRent(driverRent);
                    vehicle.setImage(image.getOriginalFilename());

                      fileStorageService.storeFile(image);
                    vehicleService.addVehicle(vehicle);
                return ResponseEntity.ok("vehicle added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding vehicle");
        }
    }


}
