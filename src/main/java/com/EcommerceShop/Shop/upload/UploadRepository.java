package com.EcommerceShop.Shop.upload;

import com.EcommerceShop.Shop.upload.entity.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends JpaRepository<Upload,Long> {
}
