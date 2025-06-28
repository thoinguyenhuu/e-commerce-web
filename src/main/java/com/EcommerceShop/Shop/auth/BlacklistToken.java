package com.EcommerceShop.Shop.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blacklist_token")
public class BlacklistToken  implements Serializable {
    @Id
    @Column(name = "id")
    String token ;

    @Column(name = "expires_at")
    Date expired_date ;
}
