package com.tul.shop.util

class Constants {
    companion object{
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE+ URL_API_VERSION

        const val URL_BASE_PRODUCT = "$URL_BASE/product"
        const val URL_BASE_CART = "$URL_BASE/cart"
        const val URL_BASE_PRODUCT_CART = "$URL_BASE/product-cart"
        const val URL_BASE_SHOP = "$URL_BASE/shop"

        const val URL_WITHOUT_ID = "/"
        const val URL_WITH_ID = "/{id}"
        const val URL_NEW_CART = "/new-cart"
        const val URL_CHECKOUT_CART = "/checkout$URL_WITH_ID"

        const val URL_BY_CART = "/by-cart$URL_WITH_ID"
        const val URL_BY_PRODUCT = "/by-product$URL_WITH_ID"

    }
}