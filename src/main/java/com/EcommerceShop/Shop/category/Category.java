package com.EcommerceShop.Shop.category;

import com.EcommerceShop.Shop.product.Entity.ProductCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "category")
public class Category  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    String id;

    @Column(name = "name")
    String name ;

    @Column(name = "description")
    String description ;

    @Column(name = "urlImage")
    String urlImage ;
    //Link
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductCategory> productCategories ;
}
