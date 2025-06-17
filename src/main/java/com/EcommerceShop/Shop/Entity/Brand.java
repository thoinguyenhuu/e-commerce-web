package com.EcommerceShop.Shop.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Filter;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "brand")
public class Brand {

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

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    List<Product> productList ;

}
