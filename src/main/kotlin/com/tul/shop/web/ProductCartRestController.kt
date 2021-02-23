package com.tul.shop.web

import com.tul.shop.business.IProductCartBusiness
import com.tul.shop.exception.BusinessException
import com.tul.shop.exception.NotFoundException
import com.tul.shop.model.ProductCart
import com.tul.shop.util.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PRODUCT_CART)
class ProductCartRestController {

    @Autowired
    val productCartBusiness: IProductCartBusiness? = null

    @GetMapping(Constants.URL_WITHOUT_ID)
    fun list(): ResponseEntity<Any>{
        return try{
            ResponseEntity(productCartBusiness!!.list(),HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping(Constants.URL_WITH_ID)
    fun load(@PathVariable("id") id:Int): ResponseEntity<Any>{
        return try{
            ResponseEntity(productCartBusiness!!.find(id),HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping(Constants.URL_WITHOUT_ID)
    fun insert(@RequestBody p: ProductCart): ResponseEntity<Any> {
        return try{
            productCartBusiness!!.save(p)
            val rh = HttpHeaders()
            rh.set("location",Constants.URL_BASE_PRODUCT_CART+"/"+p.id)
            ResponseEntity(rh,HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
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

    @GetMapping(Constants.URL_BY_CART)
    fun byCart(@PathVariable("id") id:Int): ResponseEntity<Any>{
        return try{
            ResponseEntity(productCartBusiness!!.byCart(id),HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping(Constants.URL_BY_PRODUCT)
    fun byProduct(@PathVariable("id") id:Int): ResponseEntity<Any>{
        return try{
            ResponseEntity(productCartBusiness!!.byProduct(id),HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}