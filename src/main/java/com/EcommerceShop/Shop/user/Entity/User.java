package com.EcommerceShop.Shop.user.Entity;

import com.EcommerceShop.Shop.address.Address;
import com.EcommerceShop.Shop.cart.Entity.Cart;
import com.EcommerceShop.Shop.feedback.Feedback;
import com.EcommerceShop.Shop.order.Entity.Orders;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    String id ;

    @Column(name = "username", nullable = false, unique = true)
    String username ;

    @Column(name = "email" , unique = true)
    @Email(message = "Email không hợp lệ!")
    String email  ;

    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "Password must have min 8")
    String password ;

    @Column(name = "first_name" )
    String firstName ;

    @Column(name = "last_name" )
    String lastName ;

    @Column(name = "dob")
    LocalDate dob ;

    //Link
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role ;



    // ////////////////////////////////////////////////////
    // //        //                                       //
    // //      //   /                                      //
    // //       ////                                        //
    // ///////////////////////////////////////////////////////

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Cart carts ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Orders> orders  ;

    @OneToMany(mappedBy = "user" , cascade =  CascadeType.ALL, orphanRemoval = true)
    List<Feedback> feedbacks ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Address> addresses ;
    // ////////////////////////////////////////////////////
    // //                                               //
    // //                                               //
    // //                                               //
    // ///////////////////////////////////////////////////
}
