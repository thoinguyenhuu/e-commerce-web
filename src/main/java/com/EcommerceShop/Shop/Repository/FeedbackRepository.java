package com.EcommerceShop.Shop.Repository;

import com.EcommerceShop.Shop.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,String> {
}
