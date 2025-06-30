package com.EcommerceShop.Shop.upload;

import com.EcommerceShop.Shop.upload.dto.request.UploadRequest;
import com.EcommerceShop.Shop.upload.dto.response.UploadResponse;
import com.EcommerceShop.Shop.util.ApiResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
//@RequestMapping("/upload")
public class UploadController {
    @Autowired
    UploadService uploadService ;
    @PostMapping(value = "/upload")
    ApiResponseWrapper<?> upload(@Valid @RequestPart("file") MultipartFile file ,
                                 @Valid @RequestPart("fileType") String fileType ,
                                 @Valid @RequestPart("ownerType") String ownerType ,
                                 @Valid @RequestPart("ownerId") String ownerId ) throws IOException {
        UploadRequest request = UploadRequest.builder()
                .file(file)
                .fileType(fileType)
                .ownerId(ownerId)
                .ownerType(ownerType).build() ;
        return ApiResponseWrapper.builder()
                .data(uploadService.upload(request.getFile(), request))
                   .build();
    }
}
