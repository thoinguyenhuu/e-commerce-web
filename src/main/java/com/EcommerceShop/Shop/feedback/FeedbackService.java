package com.EcommerceShop.Shop.feedback;

import com.EcommerceShop.Shop.exception.AppException;
import com.EcommerceShop.Shop.exception.ErrorCode;
import com.EcommerceShop.Shop.feedback.dto.request.FeedbackCreateRequest;
import com.EcommerceShop.Shop.feedback.dto.request.FeedbackUpdateRequest;
import com.EcommerceShop.Shop.feedback.dto.response.FeedbackDTO;
import com.EcommerceShop.Shop.feedback.dto.response.FeedbackFullInfoResponse;
import com.EcommerceShop.Shop.feedback.mapper.FeedbackMapper;
import com.EcommerceShop.Shop.order.Entity.OrderItem;
import com.EcommerceShop.Shop.order.Repository.OrderItemRepository;
import com.EcommerceShop.Shop.product.Entity.Product;
import com.EcommerceShop.Shop.product.repository.ProductRepository;
import com.EcommerceShop.Shop.user.Entity.User;
import com.EcommerceShop.Shop.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FeedbackService {
    FeedbackRepository feedbackRepository  ;
    UserRepository userRepository ;
    OrderItemRepository orderItemRepository ;
    FeedbackMapper feedbackMapper ;
    ProductRepository productRepository ;

    @Transactional
    FeedbackFullInfoResponse createFeedback(Long productId, FeedbackCreateRequest request){
        String userId = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getClaims().get("id").toString();
        OrderItem orderItem = orderItemRepository.findByUserAndProduct(userId,productId) ;
        if ( orderItem == null){
            throw new AppException(ErrorCode.CHUA_MUA_HANG);
        }
        if(orderItem.getFeedback() != null){
            throw new AppException(ErrorCode.DA_CO_FEEDBACK) ;
        }
        User user = userRepository.findById(userId).orElseThrow() ;
        Product product = productRepository.findById(productId).orElseThrow() ;
        Feedback feedback = feedbackMapper.toFeedback(request) ;

        feedback.setUser(user);
        feedback.setProduct(product);
        feedback.setOrderItem(orderItem);

        if(user.getFeedbacks() == null){
            user.setFeedbacks(new ArrayList<>());
        }
        if(product.getFeedback() == null){
            product.setFeedback(new ArrayList<>());
        }
        user.getFeedbacks().add(feedback);
        product.getFeedback().add(feedback) ;
        orderItem.setFeedback(feedback);
        product.setAverageRate(
                (feedback.getRate() + product.getAverageRate()*(product.getFeedback().size() - 1))
                /product.getFeedback().size()
        );
        return feedbackMapper.toFeedbackResponse(feedbackRepository.save(feedback)) ;
    }


    List<FeedbackDTO> getListFeedbackByProduct(Long productId, Pageable pageable){
        return feedbackRepository.getByProduct(productId, pageable).getContent().stream().map(feedbackMapper::toFeedbackDTO).toList() ;
    }

    FeedbackFullInfoResponse update(String feedbackId, FeedbackUpdateRequest request){
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> new AppException(ErrorCode.FEEDBACK_KHONG_TON_TAI)) ;
        String userId = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaims().get("id").toString() ;
        if(!userId.equals(feedback.getUser().getId())){
            throw new AppException(ErrorCode.UNAUTHORIZED) ;
        }
        if(request.getRate() != null){
            Product product = feedback.getProduct() ;
            product.setAverageRate(
                    ( product.getAverageRate()*(product.getFeedback().size()) - feedback.getRate() + request.getRate())
                            /(product.getFeedback().size() )
            );
        }

        feedbackMapper.update(feedback,request);
        return feedbackMapper.toFeedbackResponse(feedbackRepository.save(feedback)) ;
    }

    @Transactional
    void deleteFeedback(String feedbackId){
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow() ;
        String userId = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaims().get("id").toString() ;
        if (!feedback.getUser().getId().equals(userId)){
            throw new AppException(ErrorCode.UNAUTHORIZED) ;
        }
        Product product = feedback.getProduct() ;
        if(product.getFeedback().size() <=1 ){
            product.setAverageRate(0.0);
        }
        else {
            product.setAverageRate(
                    ( product.getAverageRate()*(product.getFeedback().size()) - feedback.getRate() )
                            /(product.getFeedback().size() - 1)
            );
        }
        product.getFeedback().remove(feedback);
        feedback.getOrderItem().setFeedback(null);
//        productRepository.save(product) ;
        feedbackRepository.delete(feedback);
    }

}
