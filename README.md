# 🛒 Ecommerce Shop - Spring Boot Project

Dự án web thương mại điện tử đơn giản sử dụng Spring Boot, Maven và MySQL.

---

## 🚀 Build & Run

### ✅ Yêu cầu

- Java 17+
- Maven 3.8+
- MySQL

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
    │               ├── config/
    │               ├── controller/    
    │               ├── dto/
    │               │   └── request/
    │               │   └── response/
    │               ├── entity
    │               ├── enums
    │               ├── exception/
    │               ├── mapper/
    │               ├── repository/
    │               ├── service/
    │               │   └── serviceImpl
    │               │   └── v3Service
    │               └── ShopApplication.java
    └── resources/
        ├── application.yml
└── test/
.env

```
