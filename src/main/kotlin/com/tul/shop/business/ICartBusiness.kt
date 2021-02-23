package com.tul.shop.business

import com.tul.shop.model.Cart

interface ICartBusiness {

    fun list(): List<Cart>
    fun find(id:Int): Cart
    fun save(p: Cart): Cart
    fun delete(id:Int)

}