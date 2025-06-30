package com.EcommerceShop.Shop.upload;

import com.EcommerceShop.Shop.CloudDinary.CloudinaryService;
import com.EcommerceShop.Shop.upload.dto.request.UploadRequest;
import com.EcommerceShop.Shop.upload.dto.response.UploadResponse;
import com.EcommerceShop.Shop.upload.entity.OwnerType;
import com.EcommerceShop.Shop.upload.entity.Upload;
import com.EcommerceShop.Shop.upload.entity.FileType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UploadService {
    UploadRepository uploadRepository ;

    @Autowired
    CloudinaryService cloudinaryService ;

    @PreAuthorize("#request.ownerType == 'PRODUCT' || #request.ownerType == 'CATEGORY' ? hasRole('ADMIN') : hasRole('USER')")
    public UploadResponse upload(MultipartFile filePart , UploadRequest request) throws IOException {
        var folder = request.getFileType().toLowerCase() ;
        var url = cloudinaryService.uploadFile(filePart,folder) ;
        FileType fileType = FileType.valueOf(request.getFileType().toUpperCase()) ;
        OwnerType ownerType = OwnerType.valueOf(request.getOwnerType().toUpperCase()) ;
        Upload upload = Upload.builder()
                .ownerId(request.getOwnerId())
                .fileType(fileType)
                .ownerType(ownerType)
                .url(url)
                .build();
        upload = uploadRepository.save(upload) ;
        return UploadResponse.builder()
                .ownerType(ownerType.name())
                .ownerId(upload.getOwnerId())
                .uploadId(upload.getId())
                .url(url).build();
    }


}
