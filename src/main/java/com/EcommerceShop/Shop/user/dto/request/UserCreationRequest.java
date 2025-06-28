package com.EcommerceShop.Shop.user.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String username ;

    @Size(min = 8, message = "Password must have min 8")
    String password ;

    String firstName ;
    String lastName ;
    Date dob ;
}
