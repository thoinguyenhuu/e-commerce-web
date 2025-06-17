package com.EcommerceShop.Shop.DTO.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String username ;
    String password ;
    String firstName ;
    String lastName ;
    Date dob ;
}
