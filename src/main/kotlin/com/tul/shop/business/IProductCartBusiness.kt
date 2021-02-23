package com.tul.shop.business

import com.tul.shop.model.ProductCart

interface IProductCartBusiness {

    fun list(): List<ProductCart>
    fun find(id:Int): ProductCart
    fun save(p: ProductCart): ProductCart
    fun delete(id:Int)
    fun byCart(id:Int): List<ProductCart>
    fun byProduct(id:Int): List<ProductCart>
}