package com.EcommerceShop.Shop.product;

import com.EcommerceShop.Shop.brand.Brand;
import com.EcommerceShop.Shop.brand.BrandRepository;
import com.EcommerceShop.Shop.category.Category;
import com.EcommerceShop.Shop.exception.AppException;
import com.EcommerceShop.Shop.exception.ErrorCode;
import com.EcommerceShop.Shop.product.Entity.Product;
import com.EcommerceShop.Shop.product.Entity.ProductCategory;
import com.EcommerceShop.Shop.product.Entity.ProductDetail;
import com.EcommerceShop.Shop.product.dto.request.ProductDetailRequest;
import com.EcommerceShop.Shop.product.dto.request.ProductRequest;
import com.EcommerceShop.Shop.product.dto.request.UpdateProductDetailRequest;
import com.EcommerceShop.Shop.product.dto.response.ProductResponse;
import com.EcommerceShop.Shop.product.repository.ProductRepository;
import com.EcommerceShop.Shop.category.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    CategoryService categoryService;
    BrandRepository brandRepository ;

    ProductMapper productMapper;

    @CacheEvict(value = "products", allEntries = true)
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
    @CacheEvict(value = "products", allEntries = true)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse addADetailToProduct(Long productId, ProductDetailRequest request) {
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
    @CacheEvict(value = "products", allEntries = true)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProductInfo(Long id, ProductRequest request){
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)) ;
        productMapper.update(product,request);
        return productMapper.toProductResponse(product) ;
    }

    @CacheEvict(value = "products", allEntries = true)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProductDetail(Long productId, UpdateProductDetailRequest request){
        Product product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)) ;
        ProductDetail productDetail = product.getProductDetails().stream().filter(detail -> detail.getId().equals(request.getId())).findFirst().orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)) ;

        if (request.getInfo() != null) productDetail.setInfo(request.getInfo());
        if (request.getPrice() != null) productDetail.setPrice(request.getPrice());
        if (request.getQuantity() != null) productDetail.setQuantity(request.getQuantity());

        return productMapper.toProductResponse(product) ;
    }

    @Cacheable(value = "products", key = "'page=' + #pageable.pageNumber + '&size=' + #pageable.pageSize")
    public List<ProductResponse> getProductPaging(Pageable pageable) {
        return productRepository.findAll(pageable.isPaged() ? pageable : Pageable.unpaged() ).stream().map(productMapper::toProductResponse).toList() ;
    }

    @CacheEvict(value = "products", allEntries = true)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND))  ;
        productRepository.delete(product);
    }

    public ProductResponse getProductById(Long productId){
        return productMapper.toProductResponse(productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND))) ;
    }

    @CacheEvict(value = "products", allEntries = true)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDetail(Long productId, Long detailId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)) ;
        ProductDetail item = product.getProductDetails().stream().filter(detail -> detail.getId().equals(detailId)).findFirst().orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)) ;
        product.getProductDetails().remove(item) ;
        productRepository.save(product) ;
    }
}
