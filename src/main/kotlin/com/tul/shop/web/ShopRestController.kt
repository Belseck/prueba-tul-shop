package com.tul.shop.web

import com.tul.shop.business.ICartBusiness
import com.tul.shop.business.IProductBusiness
import com.tul.shop.business.IProductCartBusiness
import com.tul.shop.exception.BusinessException
import com.tul.shop.exception.NotFoundException
import com.tul.shop.model.Cart
import com.tul.shop.model.ProductCart
import com.tul.shop.model.Shop
import com.tul.shop.util.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_SHOP)
class ShopRestController {

    @Autowired
    val productBusiness: IProductBusiness? = null

    @Autowired
    val cartBusiness: ICartBusiness? = null

    @Autowired
    val productCartBusiness: IProductCartBusiness? = null

    @GetMapping(Constants.URL_WITHOUT_ID)
    fun index():ResponseEntity<List<Any>>{
        return try{
            val s:MutableList<Any> = mutableListOf()
            val pc = productCartBusiness!!.list()
            pc.forEach {
                val sAux = Shop(it.id, it.product.id,it.cart.id,it.quantity)
                s.add(sAux)
            }
            ResponseEntity(s,HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping(Constants.URL_WITH_ID)
    fun load(@PathVariable("id") id:Int): ResponseEntity<Any>{
        return try{
            val it = productCartBusiness!!.find(id)
            val s = Shop(it.id, it.product.id,it.cart.id,it.quantity)
            ResponseEntity(s,HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping(Constants.URL_WITHOUT_ID)
    fun insert(@RequestBody s: Shop): ResponseEntity<Any> {
        return try{
            val p = productBusiness!!.find(s.product_id)
            val c = cartBusiness!!.find(s.cart_id)
            val rh = HttpHeaders()
            if(c.status == "completed"){
                ResponseEntity(HttpStatus.LOCKED)
            }else{
                val pc = ProductCart(p,c,s.quantity)
                productCartBusiness!!.save(pc)
                rh.set("location",Constants.URL_BASE_SHOP+"/"+pc.id)
                ResponseEntity(rh,HttpStatus.CREATED)
            }
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping(Constants.URL_WITHOUT_ID)
    fun update(@RequestBody s: Shop): ResponseEntity<Any> {
        return try{
            val p = productBusiness!!.find(s.product_id)
            val c = cartBusiness!!.find(s.cart_id)
            val pc = ProductCart(p,c,s.quantity)
            pc.id = s.id
            val rh = HttpHeaders()
            if(s.quantity <= 0){
                productCartBusiness!!.delete(s.id)
                rh.set("location",Constants.URL_BASE_SHOP)
                ResponseEntity(rh,HttpStatus.OK)
            }else{
                productCartBusiness!!.save(pc)
                rh.set("location",Constants.URL_BASE_SHOP+"/"+pc.id)
                ResponseEntity(rh,HttpStatus.OK)
            }
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping(Constants.URL_WITH_ID)
    fun delete(@PathVariable("id") id:Int): ResponseEntity<Any> {
        return try{
            productCartBusiness!!.delete(id)
            val rh = HttpHeaders()
            rh.set("location",Constants.URL_BASE_PRODUCT_CART)
            ResponseEntity(rh,HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping(Constants.URL_NEW_CART)
    fun newCart(): ResponseEntity<Any> {
        val c = Cart()
        return try{
            cartBusiness!!.save(c)
            val rh = HttpHeaders()
            rh.set("location",Constants.URL_BASE_CART+"/"+c.id)
            ResponseEntity(rh,HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping(Constants.URL_CHECKOUT_CART)
    fun checkoutCart(@PathVariable("id") id:Int): ResponseEntity<Any>{
        return try{
            val c = cartBusiness!!.find(id)
            c.status = "completed"
            cartBusiness!!.save(c)
            val rh = HttpHeaders()
            rh.set("location",Constants.URL_BASE_CART+"/"+c.id)
            ResponseEntity(rh,HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @GetMapping(Constants.URL_BY_CART)
    fun byCart(@PathVariable("id") id:Int): ResponseEntity<Any>{
        return try{
            val s:MutableList<Any> = mutableListOf()
            val pc = productCartBusiness!!.byCart(id)
            pc.forEach {
                val sAux = Shop(it.id, it.product.id,it.cart.id,it.quantity)
                s.add(sAux)
            }
            ResponseEntity(s,HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping(Constants.URL_BY_PRODUCT)
    fun byProduct(@PathVariable("id") id:Int): ResponseEntity<Any>{
        return try{
            val s:MutableList<Any> = mutableListOf()
            val pc = productCartBusiness!!.byProduct(id)
            pc.forEach {
                val sAux = Shop(it.id, it.product.id,it.cart.id,it.quantity)
                s.add(sAux)
            }
            ResponseEntity(s,HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}