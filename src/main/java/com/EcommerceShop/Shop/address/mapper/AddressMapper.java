package com.EcommerceShop.Shop.address.mapper;

import com.EcommerceShop.Shop.address.Address;
import com.EcommerceShop.Shop.address.dto.request.AddressRequest;
import com.EcommerceShop.Shop.address.dto.response.AddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public abstract class AddressMapper {
    @Mapping(source = "isDefault", target = "isDefault")
    public abstract Address toAddress(AddressRequest request) ;

    @Mapping(source = "isDefault", target = "isDefault")
    public abstract AddressResponse toAddressResponse(Address address) ;

    @Mapping(source = "isDefault", target = "isDefault")
    public abstract void update(@MappingTarget Address address, AddressRequest request) ;
}
