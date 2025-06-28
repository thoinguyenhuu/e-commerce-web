package com.EcommerceShop.Shop.user.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id ;
    String username ;
    String firstName ;
    String lastName ;
    Date dob ;
    String role ;
}
