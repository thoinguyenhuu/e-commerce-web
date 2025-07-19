package com.EcommerceShop.Shop.feedback;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,String> {
    @Query(value = """
            SELECT *
            FROM feedback
            WHERE product_id = :productId
            """,
            countQuery = """
                    SELECT COUNT(*) FROM feedback WHERE product_id = :productId
                    """

            ,nativeQuery = true)
    Page<Feedback> getByProduct(@Param("productId") Long productId, Pageable pageable) ;
}
