package com.EcommerceShop.Shop.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    String id ;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL , optional = false)
    @JoinColumn(name = "user_id")
    User user ;

    @Column(name = "status")
    String status ;

    @Column(name = "createAt")
    Date createdAt ;


    // Link
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,orphanRemoval = true)
    List<OrderProduct> orderProducts ;

    @OneToOne
    Feedback feedback ;
}
