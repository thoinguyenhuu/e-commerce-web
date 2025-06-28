package com.EcommerceShop.Shop.brand;

import com.EcommerceShop.Shop.product.Entity.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "brand")
public class Brand  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id ;

    @Column(name = "name")
    String name ;

    @Column(name = "description")
    String description ;

    @Column(name = "logo")
    String logoUrl ;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Product> productList ;

}
