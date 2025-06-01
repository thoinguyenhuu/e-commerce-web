# Hello
## chạy ở local thì vào này http://localhost:8080/shop-api/swagger-ui/index.html để xem api hiện có 
### swagger chưa config đầy đủ, xem tạm thôi

# Database tạm thời hiện tại 
# Database_shop_basic documentation
## Summary

- [Introduction](#introduction)
- [Database Type](#database-type)
- [Table Structure](#table-structure)
	- [user](#user)
	- [product](#product)
	- [role](#role)
	- [user_role](#user_role)
	- [Cart](#cart)
	- [cart_product](#cart_product)
	- [category](#category)
	- [product_detail](#product_detail)
	- [order](#order)
	- [order_product](#order_product)
	- [product_category](#product_category)
	- [Feedback](#feedback)
	- [blacklist_token](#blacklist_token)
- [Relationships](#relationships)
- [Database Diagram](#database-diagram)

## Introduction

## Database type

- **Database system:** MySQL
## Table structure

### user

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | VARCHAR(255) | 🔑 PK, not null, unique | fk_user_id_user_role,fk_user_id_product,fk_user_id_Cart,fk_user_id_order,fk_user_id_Feedback | |
| **username** | VARCHAR(255) | null |  | |
| **password** | VARCHAR(255) | null |  | |
| **firt** | VARCHAR(255) | null |  | |
| **lastName** | VARCHAR(255) | null |  | |
| **dob** | DATE | null |  | | 


### product

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | VARCHAR(255) | 🔑 PK, not null, unique | fk_product_id_product_detail,fk_product_id_order_product,fk_product_id_product_category | |
| **name** | VARCHAR(255) | null |  | |
| **user_id** | VARCHAR(255) | null |  | |
| **image_url** | VARCHAR(255) | null |  | |
| **average_rate** | FLOAT | null |  | |
| **description** | VARCHAR(255) | null |  | | 


### role

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | VARCHAR(255) | 🔑 PK, not null, unique | fk_role_id_user_role | |
| **name** | VARCHAR(255) | null |  | | 


### user_role

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **user_id** | VARCHAR(255) | null |  | |
| **role_id** | VARCHAR(255) | null |  | |
| **id** | VARCHAR(255) | 🔑 PK, null |  | | 


### Cart

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | VARCHAR(255) | 🔑 PK, not null, unique | fk_Cart_id_cart_product | |
| **user_id** | VARCHAR(255) | null |  | | 


### cart_product

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **cart_id** | VARCHAR(255) | null |  | |
| **product_id** | VARCHAR(255) | null | fk_cart_product_product_id_product | |
| **id** | VARCHAR(255) | 🔑 PK, null |  | |
| **num** | INTEGER | null |  | | 


### category

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | VARCHAR(255) | 🔑 PK, not null, unique |  | |
| **name** | VARCHAR(255) | null |  | |
| **description** | VARCHAR(255) | null |  | | 


### product_detail

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | VARCHAR(255) | 🔑 PK, not null, unique |  | |
| **info** | VARCHAR(255) | null |  | |
| **product_id** | VARCHAR(255) | null |  | |
| **price** | INTEGER | null |  | |
| **quantity** | INTEGER | null |  | | 


### order

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | VARCHAR(255) | 🔑 PK, not null, unique | fk_order_id_order_product,fk_order_id_Feedback | |
| **user_id** | VARCHAR(255) | null |  | |
| **status** | VARCHAR(255) | null |  | |
| **createAt** | TIMESTAMP | null |  | | 


### order_product

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **order_id** | VARCHAR(255) | null, unique |  | |
| **product_id** | VARCHAR(255) | null |  | |
| **num** | INTEGER | null |  | |
| **id** | VARCHAR(255) | 🔑 PK, not null |  | | 


### product_category

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **product_id** | VARCHAR(255) | null, unique |  | |
| **category_id** | VARCHAR(255) | null | fk_product_category_category_id_category | |
| **id** | VARCHAR(255) | 🔑 PK, null |  | | 


### Feedback

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | VARCHAR(255) | 🔑 PK, not null, unique |  | |
| **user_id** | VARCHAR(255) | null |  | |
| **product_id** | VARCHAR(255) | null | fk_Feedback_product_id_product | |
| **rate** | INTEGER | null |  | |
| **comment** | MULTILINESTRING | null |  | |
| **order_id** | VARCHAR(255) | null |  | | 


### blacklist_token

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **token** | VARCHAR(255) | 🔑 PK, null |  | |
| **date** | DATE | null |  | | 


## Relationships

- **user to user_role**: one_to_many
- **role to user_role**: one_to_many
- **user to product**: one_to_many
- **user to Cart**: one_to_many
- **Cart to cart_product**: one_to_many
- **cart_product to product**: many_to_one
- **product to product_detail**: one_to_many
- **user to order**: one_to_many
- **order to order_product**: one_to_many
- **product to order_product**: one_to_many
- **product to product_category**: one_to_many
- **product_category to category**: many_to_one
- **user to Feedback**: one_to_many
- **Feedback to product**: many_to_one
- **order to Feedback**: one_to_one

## Database Diagram

```mermaid
erDiagram
	user ||--o{ user_role : references
	role ||--o{ user_role : references
	user ||--o{ product : references
	user ||--o{ Cart : references
	Cart ||--o{ cart_product : references
	cart_product }o--|| product : references
	product ||--o{ product_detail : references
	user ||--o{ order : references
	order ||--o{ order_product : references
	product ||--o{ order_product : references
	product ||--o{ product_category : references
	product_category }o--|| category : references
	user ||--o{ Feedback : references
	Feedback }o--|| product : references
	order ||--|| Feedback : references

	user {
		VARCHAR(255) id
		VARCHAR(255) username
		VARCHAR(255) password
		VARCHAR(255) firt
		VARCHAR(255) lastName
		DATE dob
	}

	product {
		VARCHAR(255) id
		VARCHAR(255) name
		VARCHAR(255) user_id
		VARCHAR(255) image_url
		FLOAT average_rate
		VARCHAR(255) description
	}

	role {
		VARCHAR(255) id
		VARCHAR(255) name
	}

	user_role {
		VARCHAR(255) user_id
		VARCHAR(255) role_id
		VARCHAR(255) id
	}

	Cart {
		VARCHAR(255) id
		VARCHAR(255) user_id
	}

	cart_product {
		VARCHAR(255) cart_id
		VARCHAR(255) product_id
		VARCHAR(255) id
		INTEGER num
	}

	category {
		VARCHAR(255) id
		VARCHAR(255) name
		VARCHAR(255) description
	}

	product_detail {
		VARCHAR(255) id
		VARCHAR(255) info
		VARCHAR(255) product_id
		INTEGER price
		INTEGER quantity
	}

	order {
		VARCHAR(255) id
		VARCHAR(255) user_id
		VARCHAR(255) status
		TIMESTAMP createAt
	}

	order_product {
		VARCHAR(255) order_id
		VARCHAR(255) product_id
		INTEGER num
		VARCHAR(255) id
	}

	product_category {
		VARCHAR(255) product_id
		VARCHAR(255) category_id
		VARCHAR(255) id
	}

	Feedback {
		VARCHAR(255) id
		VARCHAR(255) user_id
		VARCHAR(255) product_id
		INTEGER rate
		MULTILINESTRING comment
		VARCHAR(255) order_id
	}

	blacklist_token {
		VARCHAR(255) token
		DATE date
	}
```
