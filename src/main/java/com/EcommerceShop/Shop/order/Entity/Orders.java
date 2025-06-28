package com.EcommerceShop.Shop.order.Entity;

import com.EcommerceShop.Shop.feedback.Feedback;
import com.EcommerceShop.Shop.order.OrderStatus;
import com.EcommerceShop.Shop.user.Entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Orders  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    String id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user ;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    OrderStatus status ;

    @Column(name = "createAt")
    Date createdAt ;


    // Link
    /// //////////////////////////////////////////////
    /// /
    ///
    ///
    ///
    ///
    ///
    /// ////////////////////////////////////////////////
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,orphanRemoval = true)
    List<OrderItem> orderItems;

    @OneToOne
    Feedback feedback ;


    /// ///////////////////////////////////////////////
    /// ///                                           ///
    /// ////
    ///
    /// //////////////////////////////////////////////////

}
