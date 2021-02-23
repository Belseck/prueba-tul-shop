package com.tul.shop.model

import javax.persistence.*

@Entity
@Table(name = "carts")
data class Cart(var status:String = "pending") {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Int = 0

    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "cart")
    private val productCarts = mutableListOf<ProductCart>()

    fun addLineItem(p: ProductCart) = this.productCarts.plusAssign(p)
}