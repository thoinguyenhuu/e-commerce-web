package com.EcommerceShop.Shop.Entity;

import com.EcommerceShop.Shop.Enums.ShopStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    String id ;

    @Column(name = "name")
    String name ;

    @Column(name = "description")
    String description ;

    @OneToOne
    @JoinColumn(name = "owner_id")
    User user ;

    @Enumerated(EnumType.STRING)
    ShopStatus status ;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Product> product ;
}
