package com.EcommerceShop.Shop.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.*;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    String id ;

    @Column(name = "username", nullable = false, unique = true)
    String username ;

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
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Product> products ;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    Set<UserRole> userRoles ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Cart> carts ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Orders> orders  ;

    @OneToMany(mappedBy = "user" , cascade =  CascadeType.ALL, orphanRemoval = true)
    List<Feedback> feedbacks ;

}
