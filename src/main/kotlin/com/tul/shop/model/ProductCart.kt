package com.tul.shop.model

import javax.persistence.*
import com.fasterxml.jackson.annotation.JsonView

@Entity
@Table(name = "product_carts")
data class ProductCart(
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch=FetchType.LAZY)
    var product: Product,
    @JoinColumn(name = "cart_id")
    @ManyToOne(fetch=FetchType.LAZY)
    val cart: Cart,
    val quantity:Int = 1) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Int = 0

    /*
    @JsonView(Short::class)
    fun getProductId(): Int = this.product.id

    @JsonView(Short::class)
    fun getCartId(): Int = this.cart.id
    */
}