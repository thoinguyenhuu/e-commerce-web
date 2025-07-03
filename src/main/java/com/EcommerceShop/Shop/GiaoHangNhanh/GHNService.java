package com.EcommerceShop.Shop.GiaoHangNhanh;

import com.EcommerceShop.Shop.exception.AppException;
import com.EcommerceShop.Shop.exception.ErrorCode;
import com.EcommerceShop.Shop.GiaoHangNhanh.dto.request.GetProvinceRequest;
import com.EcommerceShop.Shop.GiaoHangNhanh.dto.request.GetWardRequest;
import com.EcommerceShop.Shop.GiaoHangNhanh.dto.request.ShippingFeeRequest;
import com.EcommerceShop.Shop.GiaoHangNhanh.dto.response.DistrictResponse;
import com.EcommerceShop.Shop.GiaoHangNhanh.dto.response.ProvinceResponse;
import com.EcommerceShop.Shop.GiaoHangNhanh.dto.response.WardResponse;
import com.EcommerceShop.Shop.util.ApiResponseWrapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GHNService {
    String token ;

    String shopId ;
    WebClient webClient ;

    public GHNService(WebClient.Builder webClient, GHNConfig ghnConfig) {
        this.token = ghnConfig.getToken() ;
        this.shopId = ghnConfig.getShop() ;
        this.webClient = webClient.baseUrl(ghnConfig.getBaseUrl()).build();
    }

    @Cacheable(value = "province")
    public List<ProvinceResponse> getProvince(){
        ApiResponseWrapper<List<ProvinceResponse>> response = webClient.get()
                .uri("/master-data/province")
                .header("token", token)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(new ParameterizedTypeReference<ApiResponseWrapper<List<ProvinceResponse>>>() {
                }))
                .block() ;

        assert response != null;
        if(response.getCode() != 200){
            throw new AppException(ErrorCode.BAD_REQUEST,response.getMessage()) ;
        }
        return response.getData() ;
    }

    @Cacheable(value = "district", key = "#provinceId")
    public List<DistrictResponse> getDistrict(Long provinceId){
        GetProvinceRequest body = GetProvinceRequest.builder()
                .province_id(provinceId).build();
        ApiResponseWrapper<List<DistrictResponse>>  response = webClient.post()
                .uri("/master-data/district")
                .header("token" ,token)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
                        new ParameterizedTypeReference<ApiResponseWrapper<List<DistrictResponse>>>(){}
                ))
                .block() ;
        assert response != null;
        if(response.getCode() != 200){
            throw new AppException(ErrorCode.BAD_REQUEST,response.getMessage()) ;
        }
        return response.getData() ;
    }

    @Cacheable(value = "ward", key = "#districtId")
    public List<WardResponse> getWard(Long districtId){
        GetWardRequest body = GetWardRequest.builder()
                .district_id(districtId).build();
        ApiResponseWrapper<List<WardResponse>>  response = webClient.post()
                .uri("/master-data/ward")
                .header("token" ,token)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
                        new ParameterizedTypeReference<ApiResponseWrapper<List<WardResponse>>>(){}
                ))
                .block() ;
        assert response != null;
        if(response.getCode() != 200){
            throw new AppException(ErrorCode.BAD_REQUEST,response.getMessage()) ;
        }
        return response.getData() ;
    }

    public Long calculateFee(ShippingFeeRequest request){
        setDefault(request);
        ApiResponseWrapper<Long> response = webClient.post()
                .uri("/v2/shipping-order/fee")
                .header("token",token)
                .header("ShopId", shopId)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(new ParameterizedTypeReference<ApiResponseWrapper<Long>>() {
                }))
                .block() ;
        assert response != null ;
        if (response.getCode() != 200) throw new AppException(ErrorCode.BAD_REQUEST, response.getMessage()) ;
        return response.getData();
    }
    private void setDefault(ShippingFeeRequest request){
        request.setHeight(15);
        request.setWidth(20);
        request.setLength(30);
        request.setWeight(1000);
        request.setService_type_id(2);
    }
}
