package com.EcommerceShop.Shop.Repository;

import com.EcommerceShop.Shop.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    Category findByName(String name) ;
}
