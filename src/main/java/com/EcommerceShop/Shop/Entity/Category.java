package com.EcommerceShop.Shop.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    String id;

    @Column(name = "name")
    String name ;

    @Column(name = "description")
    String description ;

    //Link
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductCategory> productCategories ;
}
