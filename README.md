// OPCIONES SHOP
###
GET http://localhost:8080/api/v1/shop/
###
GET http://localhost:8080/api/v1/shop/{{id}}
###
POST http://localhost:8080/api/v1/shop/
###
PUT http://localhost:8080/api/v1/shop/
###
DELETE http://localhost:8080/api/v1/shop/{{id}}
###
GET http://localhost:8080/api/v1/shop/new-cart/
###
PUT http://localhost:8080/api/v1/shop/checkout/{{id}}
###
GET http://localhost:8080/api/v1/shop/by-cart/{{id}}
###
GET http://localhost:8080/api/v1/shop/by-product/{{id}}


// OPCIONES CRUD CART
###
GET http://localhost:8080/api/v1/cart/
###
GET http://localhost:8080/api/v1/cart/{{id}}
###
POST http://localhost:8080/api/v1/cart/
###
PUT http://localhost:8080/api/v1/cart/
###
DELETE http://localhost:8080/api/v1/cart/{{id}}
###
GET http://localhost:8080/api/v1/cart/new-cart/
###
PUT http://localhost:8080/api/v1/cart/checkout/{{id}}


// OPCIONES CRUD PRODUCT
###
GET http://localhost:8080/api/v1/product/
###
GET http://localhost:8080/api/v1/product/{{id}}
###
POST http://localhost:8080/api/v1/product/
###
PUT http://localhost:8080/api/v1/product/
###
DELETE http://localhost:8080/api/v1/product/{{id}}


// OPCIONES CRUD PRODUCT_CART
###
GET http://localhost:8080/api/v1/product-cart/
###
GET http://localhost:8080/api/v1/product-cart/{{id}}
###
POST http://localhost:8080/api/v1/product-cart/
###
DELETE http://localhost:8080/api/v1/product-cart/{{id}}
###
GET http://localhost:8080/api/v1/product-cart/by-cart/{{id}}
###
GET http://localhost:8080/api/v1/product-cart/by-product/{{id}}
