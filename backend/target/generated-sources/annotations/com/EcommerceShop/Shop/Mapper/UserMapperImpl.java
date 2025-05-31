package com.EcommerceShop.Shop.Mapper;

import com.EcommerceShop.Shop.DTO.request.UserCreationRequest;
import com.EcommerceShop.Shop.DTO.request.UserUpdateRequest;
import com.EcommerceShop.Shop.DTO.response.UserResponse;
import com.EcommerceShop.Shop.Entity.User;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-20T11:35:38+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.firstName( request.getFirstName() );
        user.lastName( request.getLastName() );
        if ( request.getDob() != null ) {
            user.dob( LocalDateTime.ofInstant( request.getDob().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.role( mapRoles( user.getUserRoles() ) );
        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.firstName( user.getFirstName() );
        userResponse.lastName( user.getLastName() );
        if ( user.getDob() != null ) {
            userResponse.dob( Date.from( user.getDob().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }

        return userResponse.build();
    }

    @Override
    public void updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getPassword() != null ) {
            user.setPassword( request.getPassword() );
        }
        if ( request.getFirstName() != null ) {
            user.setFirstName( request.getFirstName() );
        }
        if ( request.getLastName() != null ) {
            user.setLastName( request.getLastName() );
        }
        if ( request.getDob() != null ) {
            user.setDob( LocalDateTime.ofInstant( request.getDob().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
    }
}
