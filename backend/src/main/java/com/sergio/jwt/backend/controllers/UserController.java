package com.sergio.jwt.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sergio.jwt.backend.entites.Order;
import com.sergio.jwt.backend.entites.TransportOrders;
import com.sergio.jwt.backend.entites.User;
import com.sergio.jwt.backend.entites.Vehicle;
import com.sergio.jwt.backend.repositories.OrderRepository;
import com.sergio.jwt.backend.repositories.TransportOrdersRepository;
import com.sergio.jwt.backend.repositories.UserRepository;
import com.sergio.jwt.backend.repositories.VehicleRepository;
import com.sergio.jwt.backend.services.FileStorageService;
import com.sergio.jwt.backend.services.OrderService;
import com.sergio.jwt.backend.services.VehicleService;

@RestController
@CrossOrigin("*")
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    VehicleRepository vehicleRepository;
   @Autowired
   OrderService orderService;
   @Autowired
   TransportOrdersRepository transportOrdersRepository;
      @Autowired
    private FileStorageService fileStorageService;


      @GetMapping("/getUser/{id}")
    public Optional<User> getUser(@PathVariable int id){

        return userRepository.findById((long) id);
    }
    @GetMapping("/getVehicles")
    public List<Vehicle> getVehicles(){
        return vehicleService.getAllVehicls();
    }
     @GetMapping("/getVehicle/{id}")
    public Optional<Vehicle> getVehicle(@PathVariable int id){
        return vehicleRepository.findById(id);
    }

    @GetMapping("/getVehicles/travels")
    public List<Vehicle> getVehiclesTravels(){


        return vehicleRepository.findTravelsVehicles();
    }
     @GetMapping("/getVehicles/transport")
    public List<Vehicle> getVehiclesTransport(){


        return vehicleRepository.findTransportVehicles();
    }

    @GetMapping("/getVehicles/traveller")
    public List<Vehicle> getVehiclesTraveller(){
        return vehicleRepository.findTravellerVehicles();
    }

    @GetMapping("/getVehicles/bus")
    public List<Vehicle> getVehiclesBus(){
        return vehicleRepository.findBusVehicles();
    }

    @GetMapping("/getVehicles/pickUpTruck")
    public List<Vehicle> getVehiclesPickUpTruck(){
        return vehicleRepository.findPickUpTruckVehicles();
    }

    @GetMapping("/getVehicles/heavyLoad")
    public List<Vehicle> getVehiclesHeavyLoad(){
        return vehicleRepository.findHeavyLoadVehicles();
    }

    @GetMapping("/getOrders/travels")
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }
       @GetMapping("/getOrders/transports")
    public List<TransportOrders> getOrderstransports(){
        return transportOrdersRepository.findAll();
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id,
    @RequestParam("name") String name,
    @RequestParam("phoneNumber") String phoneNumber,
    @RequestParam("email") String email,
    @RequestParam("image") MultipartFile image)
{
        try{

            Optional<User> oldUser = userRepository.findById((long)id);
            //System.out.println(oldUser.get().getPassword());
            User user = new User();
            user.setId((long) id);
            user.setPassword(oldUser.get().getPassword());
            user.setRole(oldUser.get().getRole());
            user.setFullName(name);
            user.setPhoneNumber(phoneNumber);
            user.setLogin(email);
            user.setImage(image.getOriginalFilename());

            fileStorageService.storeFile(image);

            userRepository.save(user);
            return  ResponseEntity.ok("user updated successfully");
            

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user");

        }
    }
    @PostMapping("/bookVehicle")
    public String bookVehicle(@RequestParam("email") String email,
            @RequestParam("vehicleName") String vehicleName,
            @RequestParam("checkInDate") String checkInDate,
            @RequestParam("checkOutDate") String checkOutDate,
            @RequestParam("children") String children,
            @RequestParam("adult") String adult,
            @RequestParam("destination") String destination,
            @RequestParam("phone") String phone){

                try{

                    Order order = new Order();

                    order.setEmail(email);
                    order.setVehicleName(vehicleName);
                    order.setCheckInDate(checkInDate);
                    order.setCheckOutDate(checkOutDate);
                    order.setChildren(children);
                    order.setAdult(adult);
                    order.setDestination(destination);
                    order.setPhone(phone);


                    orderService.bookVehicle(order);
                     return "successfully vehicle booked!";
                }
                catch(Exception e){
                         return "error while booking vehicle";
                }
               
    }

     @PostMapping("/bookVehicleForTransport")
    public String bookVehicle(@RequestParam("email") String email,
            @RequestParam("vehicleName") String vehicleName,
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("shippingDate") String shippingDate,
            @RequestParam("packageDescription") String packageDescription,
            @RequestParam("phone") String phone){

                try{

                    TransportOrders order = new TransportOrders();

                    order.setEmail(email);
                    order.setVehicleName(vehicleName);
                   order.setFrom(from);
                   order.setTo(to);
                   order.setPackageDescription(packageDescription);
                   order.setShippingdate(shippingDate);
                    order.setPhone(phone);


                    transportOrdersRepository.save(order);

                    return "successfully vehicle booked!";
                }
                catch(Exception e){
                         return "error while booking vehicle";
                }
               
    }


    
}
