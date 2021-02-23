package com.tul.shop.model

import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(val nombre:String = "", val sku:String = "", val descripcion:String?) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Int = 0

    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "product")
    private val productCarts = mutableListOf<ProductCart>()

    fun addLineItem(p: ProductCart) = this.productCarts.plusAssign(p)
}