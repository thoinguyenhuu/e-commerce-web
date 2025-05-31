package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.ProductCreateRequest;
import com.EcommerceShop.Shop.DTO.request.ProductDetailRequest;
import com.EcommerceShop.Shop.DTO.response.ProductResponse;
import com.EcommerceShop.Shop.Entity.*;
import com.EcommerceShop.Shop.Exception.AppException;
import com.EcommerceShop.Shop.Exception.ErrorCode;
import com.EcommerceShop.Shop.Mapper.ProductMapper;
import com.EcommerceShop.Shop.Repository.ProductRepository;
import com.EcommerceShop.Shop.Repository.RoleRepository;
import com.EcommerceShop.Shop.Repository.UserRepository;
import jdk.jfr.Percentage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Data
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository ;
    UserRepository userRepository ;
    RoleRepository roleRepository ;
    ProductMapper productMapper ;
    CategoryService categoryService ;
    @PreAuthorize("hasRole('SELLER')")
    public ProductResponse create(ProductCreateRequest request){
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        User seller = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.BAD_REQUEST)) ;
        Role sellerRole = roleRepository.findByName("SELLER") ;
        if(seller.getUserRoles().stream().noneMatch(userRole -> userRole.getRole().equals(sellerRole))){
            throw new AppException(ErrorCode.UNAUTHORIZED) ;
        }
        Product product= productMapper.toProduct(request) ;
        product.setSeller(seller);
        seller.getProducts().add(product) ;
        List<ProductDetail> productDetails = request.getProductDetails().stream()
                .map(detail -> ProductDetail.builder()
                        .price(detail.getPrice())
                        .quantity(detail.getQuantity())
                        .info(detail.getInfo())
                        .product(product).build()).toList() ;
        product.getProductDetails().addAll(productDetails) ;

        List<Category> categories =  request.getProductCategory().stream()
                .map(categoryService::getByName).toList() ;
        List<ProductCategory> productCategories = categories.stream()
                .map(category -> ProductCategory.builder()
                        .product(product)
                        .category(category).build()).toList() ;
        product.getProductCategories().addAll(productCategories) ;

        return productMapper.toProductResponse(productRepository.save(product)) ;
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList() ;
    }


}
