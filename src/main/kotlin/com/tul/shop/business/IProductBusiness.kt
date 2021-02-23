package com.tul.shop.business

import com.tul.shop.model.Product

interface IProductBusiness {

    fun list(): List<Product>
    fun find(id:Int): Product
    fun save(p:Product): Product
    fun delete(id:Int)
}