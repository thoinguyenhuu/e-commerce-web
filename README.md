# 🛒 Ecommerce Shop - Spring Boot Project

Dự án web thương mại điện tử đơn giản sử dụng Spring Boot, Maven và MySQL.

---

## 🚀 Build & Run

### ✅ Yêu cầu

- Java 17+
- Maven 3.8+
- MySQL (nếu có sử dụng database)

---

## ⚙️ Công nghệ sử dụng

- 💻 Spring Boot 3+
- 🔐 Spring Security + JWT
- 🛢️ Hibernate / JPA
- 🐬 MySQL 
- 🛠️ MapStruct
- 🌐 Swagger 3 (OpenAPI)
- 🐳 Docker (optional)

---
### 🔧 Build Dự Án

```bash
mvn clean install

mvn spring-boot:run
```
## 📁 Cấu trúc thư mục chính
```
src/
└── main/
    ├── java/
    │   └── com/
    │       └── EcommerceShop/
    │           └── Shop/
    │               ├── address/              # Địa chỉ của user phục vụ việc giao hàng/ order
    │               ├── auth/                 # Xác thực và phân quyền người dùng
    │               ├── brand/                # Thương hiệu
    │               ├── cart/                 # Giỏ hàng
    │               ├── category/             # Danh mục sản phẩm
    │               ├── cloudinary/           # Api của cloudinary phục vụ việc lưu trữ ảnh online
    │               ├── exception/            # Xử lý ngoại lệ chung
    │               ├── feedback/             # Phản hồi của user sau khi mua hàng
    │               ├── giaohangnhanh/        # Gọi api của giao hàng nhanh, dùng để lấy danh sách tỉnh/thành, và tính chi phí vận chuyển
    │               ├── order/                # Đặt hàng và quản lý đơn hàng
    │               ├── product/              # Sản phẩm và chi tiết sản phẩm
    │               ├── upload/               # Lưu trữ url của ảnh, phục vụ việc xóa ảnh khi không dùng đến ( đang cân nhắc có cần feature này không)
    │               ├── user/                 # Thông tin và quản lý người dùng
    │               ├── util/                 # DTO chung, hàm tiện ích
    │               │    ├── config/          # Cấu hình Security, Redis, App
    │               │    └── ApiResponseWrapper.java
    │               └── ShopApplication.java
    └── resources/                            # File cấu hình, template, static
        ├── application.yml                   # File cấu hình    
└── test/                                     # Test unit và integration
```
