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
@Table(name = "transport_orders")
public class TransportOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


     @Column(name = "vehicle_name", nullable = false)
    @Size(max = 100)
    private String vehicleName;

     @Column(name = "shippingdate", nullable = false)
    private String shippingdate;
    
     @Column(name = "shipping_from", nullable = false)
    private String from;
    
    
     @Column(name = "shippingh_to", nullable = false)
    private String to;
    
     @Column(name = "packageDescription", nullable = false)
    private String packageDescription;


    
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

}
