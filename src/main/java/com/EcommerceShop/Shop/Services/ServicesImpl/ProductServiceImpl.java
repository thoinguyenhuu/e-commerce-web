package com.EcommerceShop.Shop.Services.ServicesImpl;

import com.EcommerceShop.Shop.DTO.request.Product.ProductRequest;
import com.EcommerceShop.Shop.DTO.request.Product.UpdateProductDetailRequest;
import com.EcommerceShop.Shop.DTO.response.ProductResponse;
import com.EcommerceShop.Shop.Entity.*;
import com.EcommerceShop.Shop.Exception.AppException;
import com.EcommerceShop.Shop.Exception.ErrorCode;
import com.EcommerceShop.Shop.Mapper.ProductMapper;
import com.EcommerceShop.Shop.Repository.*;
import com.EcommerceShop.Shop.Services.CategoryService;
import com.EcommerceShop.Shop.Services.ProductService;
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
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;
    ProductMapper productMapper;
    CategoryService categoryService;
    CategoryRepository categoryRepository ;
    ShopRepository shopRepository ;

    @PreAuthorize("hasRole('SELLER')")
    public ProductResponse create(ProductRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User seller = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.BAD_REQUEST));
        Role sellerRole = roleRepository.findByName("SELLER");
        if (seller.getUserRoles().stream().noneMatch(userRole -> userRole.getRole().equals(sellerRole))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        Product product = productMapper.toProduct(request);
        Shop shop = shopRepository.findByUser(seller) ;
        product.setShop(shop);
        shop.getProduct().add(product) ;
        List<ProductDetail> productDetails = request.getProductDetails().stream()
                .map(detail -> ProductDetail.builder()
                        .price(detail.getPrice())
                        .quantity(detail.getQuantity())
                        .info(detail.getInfo())
                        .product(product).build()).toList();
        product.getProductDetails().addAll(productDetails);

        List<Category> categories = request.getProductCategory().stream()
                .map(categoryService::getByName).toList();
        List<ProductCategory> productCategories = categories.stream()
                .map(category -> ProductCategory.builder()
                        .product(product)
                        .category(category).build()).toList();
        product.getProductCategories().addAll(productCategories);

        return productMapper.toProductResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }

    public List<ProductResponse> getByCategory(String name) {
        Category category = categoryService.getByName(name) ;
        return category.getProductCategories().stream()
                .map(productCategory -> productMapper.toProductResponse(productCategory.getProduct()))
                .toList();
    }

    @PreAuthorize("hasRole('SELLER)")
    public ProductResponse updateProductInfo(String id, ProductRequest request){
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)) ;
        productMapper.update(product,request);
        return productMapper.toProductResponse(product) ;
    }

    @PreAuthorize("hasRole('SELLER')")
    public ProductResponse updateProductDetail(String id, UpdateProductDetailRequest request){
        return ProductResponse.builder().build();
    }
}
