package com.tul.shop.web

import com.tul.shop.business.ICartBusiness
import com.tul.shop.exception.BusinessException
import com.tul.shop.exception.NotFoundException
import com.tul.shop.model.Cart
import com.tul.shop.util.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_CART)
class CartRestController {

    @Autowired
    val cartBusiness: ICartBusiness? = null

    @GetMapping(Constants.URL_WITHOUT_ID)
    fun list(): ResponseEntity<Any>{
        return try{
            ResponseEntity(cartBusiness!!.list(),HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping(Constants.URL_WITH_ID)
    fun load(@PathVariable("id") id:Int): ResponseEntity<Any>{
        return try{
            ResponseEntity(cartBusiness!!.find(id),HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping(Constants.URL_WITHOUT_ID)
    fun insert(@RequestBody c: Cart): ResponseEntity<Any> {
        return this.saveCart(c)
    }

    @PutMapping(Constants.URL_WITHOUT_ID)
    fun update(@RequestBody c: Cart): ResponseEntity<Any> {
        return try{
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

    @DeleteMapping(Constants.URL_WITH_ID)
    fun delete(@PathVariable("id") id:Int): ResponseEntity<Any> {
        return try{
            cartBusiness!!.delete(id)
            val rh = HttpHeaders()
            rh.set("location",Constants.URL_BASE_CART)
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
        return this.saveCart(c)
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

    fun saveCart(c:Cart): ResponseEntity<Any> {
        return try{
            cartBusiness!!.save(c)
            val rh = HttpHeaders()
            rh.set("location",Constants.URL_BASE_CART+"/"+c.id)
            ResponseEntity(rh,HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}