package com.EcommerceShop.Shop.product.Entity;

import com.EcommerceShop.Shop.cart.Entity.CartItem;
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
@Table(name = "product_detail")
public class ProductDetail  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    Long id ;

    @Column(name = "info")
    String info ;

    @Column(name = "price")
    double price ;

    @Column(name = "quantity")
    int quantity ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product ;

    @OneToMany(mappedBy = "item", orphanRemoval = true, cascade = CascadeType.ALL)
    List<CartItem> cart  ;
}
