package com.EcommerceShop.Shop.address;

import com.EcommerceShop.Shop.user.Entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id ;

    @Column(name = "province")
    String province ;
    Long province_id ;
    @Column(name = "district")
    String district ;
    Long district_id ;
    @Column(name = "ward")
    String ward ;
    Long ward_id ;
    @Column(name = "info")
    String info ;

    @Column(name = "receiver_name")
    String receiverName ;

    @Column(name = "phone")
    String phone ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user ;

    boolean isDefault ;

}
