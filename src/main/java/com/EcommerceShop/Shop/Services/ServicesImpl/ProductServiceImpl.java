package com.EcommerceShop.Shop.Services.ServicesImpl;

import com.EcommerceShop.Shop.DTO.request.Product.ProductDetailRequest;
import com.EcommerceShop.Shop.DTO.request.Product.ProductRequest;
import com.EcommerceShop.Shop.DTO.request.Product.UpdateProductDetailRequest;
import com.EcommerceShop.Shop.DTO.response.ProductResponse;
import com.EcommerceShop.Shop.Entity.*;
import com.EcommerceShop.Shop.Exception.AppException;
import com.EcommerceShop.Shop.Enums.ErrorCode;
import com.EcommerceShop.Shop.Mapper.ProductMapper;
import com.EcommerceShop.Shop.Repository.*;
import com.EcommerceShop.Shop.Services.CategoryService;
import com.EcommerceShop.Shop.Services.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    CategoryService categoryService;
    BrandRepository brandRepository ;

    ProductMapper productMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse create(ProductRequest request) {
        Product product = productMapper.toProduct(request);
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
        if(request.getBrand() != null){
            Optional<Brand> brand = brandRepository.findByName(request.getBrand()) ;
            if(brand.isPresent()){
                product.setBrand(brand.get());
                brand.get().getProductList().add(product) ;
//            brandRepository.save(brand) ;
            }
        }
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse addADetailToProduct(String productId, ProductDetailRequest request) {
        Product product  = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)) ;
        ProductDetail productDetail = ProductDetail.builder()
                .info(request.getInfo())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .product(product).build();
        product.getProductDetails().add(productDetail) ;
        return productMapper.toProductResponse(productRepository.save(product)) ;
    }

    public List<ProductResponse> getByCategory(String name) {
        Category category = categoryService.getByName(name) ;
        return category.getProductCategories().stream()
                .map(productCategory -> productMapper.toProductResponse(productCategory.getProduct()))
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProductInfo(String id, ProductRequest request){
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)) ;
        productMapper.update(product,request);
        return productMapper.toProductResponse(product) ;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProductDetail(String productId, UpdateProductDetailRequest request){
        Product product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)) ;
        ProductDetail productDetail = product.getProductDetails().stream().filter(detail -> detail.getId().equals(request.getId())).findFirst().orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)) ;

        if (request.getInfo() != null) productDetail.setInfo(request.getInfo());
        if (request.getPrice() != null) productDetail.setPrice(request.getPrice());
        if (request.getQuantity() != null) productDetail.setQuantity(request.getQuantity());

        return productMapper.toProductResponse(product) ;
    }

    @Override
    public List<ProductResponse> getProductPaging(Pageable pageable) {
        return productRepository.findAll(pageable.isPaged() ? pageable : Pageable.unpaged() ).stream().map(productMapper::toProductResponse).toList() ;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(String productId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND))  ;
        productRepository.delete(product);
    }

}
