package com.tul.shop

import com.tul.shop.dao.CartRepository
import com.tul.shop.dao.ProductCartRepository
import com.tul.shop.dao.ProductRepository
import com.tul.shop.model.Cart
import com.tul.shop.model.Product
import com.tul.shop.model.ProductCart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShopApplication:CommandLineRunner{

	@Autowired
	val productRepository: ProductRepository? = null

	@Autowired
	val cartRepository: CartRepository? = null

	@Autowired
	val productCartRepository: ProductCartRepository? = null

	override fun run(vararg args: String?) {
		val product1 = Product("Articulo 1","SP7875","Descripcion Articulo 1")
		val product2 = Product("Articulo 2","TR87680","Descripcion Articulo 2")
		val product3 = Product("Articulo 3","MK676554","Descripcion Articulo 3")
		val product4 = Product("Articulo 4","YE98767","Descripcion Articulo 4")
		val product5 = Product("Articulo 5","XR23423","Descripcion Articulo 5")
		val product6 = Product("Articulo 6","PW98762","Descripcion Articulo 6")
		val product7 = Product("Articulo 7","BM87684","Descripcion Articulo 7")
		val product8 = Product("Articulo 8","BH67655","Descripcion Articulo 8")
		val product9 = Product("Articulo 9","WT98768","Descripcion Articulo 9")
		val product10 = Product("Articulo 10","TS3456","Descripcion Articulo 10")
		val product11 = Product("Articulo 11","WDG123","Descripcion Articulo 11")

		productRepository!!.save(product1)
		productRepository!!.save(product2)
		productRepository!!.save(product3)
		productRepository!!.save(product4)
		productRepository!!.save(product5)
		productRepository!!.save(product6)
		productRepository!!.save(product7)
		productRepository!!.save(product8)
		productRepository!!.save(product9)
		productRepository!!.save(product10)
		productRepository!!.save(product11)

		val cart1 = Cart()
		val cart2 = Cart()
		val cart3 = Cart()

		cartRepository!!.save(cart1)
		cartRepository!!.save(cart2)
		cartRepository!!.save(cart3)

		val productCart1 = ProductCart(product1,cart1,10)
		val productCart2 = ProductCart(product2,cart2,2)
		val productCart3 = ProductCart(product3,cart1,5)
		val productCart4 = ProductCart(product4,cart2,7)
		val productCart5 = ProductCart(product5,cart1,8)
		val productCart6 = ProductCart(product6,cart2)
		val productCart7 = ProductCart(product7,cart1)

		productCartRepository!!.save(productCart1)
		productCartRepository!!.save(productCart2)
		productCartRepository!!.save(productCart3)
		productCartRepository!!.save(productCart4)
		productCartRepository!!.save(productCart5)
		productCartRepository!!.save(productCart6)
		productCartRepository!!.save(productCart7)
	}
}

fun main(args: Array<String>) {
	runApplication<ShopApplication>(*args)
}
