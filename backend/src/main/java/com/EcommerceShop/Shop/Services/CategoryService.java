package com.EcommerceShop.Shop.Services;

import com.EcommerceShop.Shop.DTO.request.CategoryCreateRequest;
import com.EcommerceShop.Shop.DTO.response.CategoryResponse;
import com.EcommerceShop.Shop.Entity.Category;
import com.EcommerceShop.Shop.Exception.AppException;
import com.EcommerceShop.Shop.Exception.ErrorCode;
import com.EcommerceShop.Shop.Mapper.CategoryMapper;
import com.EcommerceShop.Shop.Repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.tomcat.util.IntrospectionUtils.capitalize;

@Slf4j
@Service
@Data
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository ;
    CategoryMapper categoryMapper ;

    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse create(CategoryCreateRequest request){
        request.setName(formatText(request.getName()));
        log.info(formatText(request.getName()));
        if(categoryRepository.findByName(request.getName()) != null){
            throw new AppException(ErrorCode.CATEGORY_EXISTED) ;
        }

        Category category = categoryMapper.toCategory(request)  ;
        categoryRepository.save(category) ;
        return categoryMapper.toCategoryResponse(category) ;
    }

    public List<CategoryResponse> getALl(){
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).toList() ;
    }
    public  Category getByName(String name){
        return categoryRepository.findByName(name) ;
    }
    private String formatText(String text){
        if(text == null || text.isBlank()) return text ;
        text = text.toLowerCase() ;
        String[] words = text.trim().split(" ") ;
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(capitalize(word)).append(" ");
            }
        }
        return result.toString().trim();
    }
}
