package com.EcommerceShop.Shop.address.service;

import com.EcommerceShop.Shop.address.Address;
import com.EcommerceShop.Shop.address.AddressRepository;
import com.EcommerceShop.Shop.address.dto.request.AddressRequest;
import com.EcommerceShop.Shop.address.dto.response.AddressResponse;
import com.EcommerceShop.Shop.address.mapper.AddressMapper;
import com.EcommerceShop.Shop.exception.AppException;
import com.EcommerceShop.Shop.exception.ErrorCode;
import com.EcommerceShop.Shop.user.Entity.User;
import com.EcommerceShop.Shop.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class AddressService {
    AddressRepository addressRepository ;
    UserRepository userRepository ;
    AddressMapper addressMapper ;
    public AddressResponse createAddress(String userId, AddressRequest request){
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)) ;
        Address address = addressMapper.toAddress(request) ;
        if(request.getIsDefault() == null){
            address.setIsDefault(false);
        }
        address.setUser(user);
        user.getAddresses().add(address) ;
        return addressMapper.toAddressResponse(addressRepository.save(address));
    }

    public List<AddressResponse> getAll(String userId){
        return userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)).getAddresses().stream()
                .map(addressMapper::toAddressResponse).toList() ;
    }

    public AddressResponse update(String userId, Long addressId, AddressRequest request){
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new AppException(ErrorCode.ADDRESS_ID_WRONG)) ;
        addressMapper.update(address,request);
        return addressMapper.toAddressResponse(address) ;
    }

    public void delete(String userId, Long addressId){
        //?

        addressRepository.deleteById(addressId);
    }

}
