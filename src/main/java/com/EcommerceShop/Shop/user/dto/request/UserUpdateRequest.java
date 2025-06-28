package com.EcommerceShop.Shop.user.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password ;
    String firstName ;
    String lastName ;
    Date dob ;
}
