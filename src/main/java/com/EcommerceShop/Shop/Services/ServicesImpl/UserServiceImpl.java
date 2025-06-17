package com.EcommerceShop.Shop.Services.ServicesImpl;


import com.EcommerceShop.Shop.DTO.request.UserCreationRequest;
import com.EcommerceShop.Shop.DTO.request.UserUpdateRequest;
import com.EcommerceShop.Shop.DTO.response.UserResponse;
import com.EcommerceShop.Shop.Entity.User;
import com.EcommerceShop.Shop.Enums.Role;
import com.EcommerceShop.Shop.Exception.AppException;
import com.EcommerceShop.Shop.Exception.ErrorCode;
import com.EcommerceShop.Shop.Mapper.UserMapper;
import com.EcommerceShop.Shop.Repository.UserRepository;
import com.EcommerceShop.Shop.Services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository ;
    UserMapper userMapper ;
    PasswordEncoder passwordEncoder ;

    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new AppException(ErrorCode.USER_EXISTED) ;
        }
        User user = userMapper.toUser(request) ;
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user) ;
        return userMapper.toUserResponse(user) ;
    }

    public UserResponse updateUser(String id, UserUpdateRequest request){
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        userMapper.updateUser(user,request);
        if(request.getPassword() != null){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        userRepository.save(user) ;
        return userMapper.toUserResponse(user) ;
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList() ;
    }

    public UserResponse getMyInfo(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

}
