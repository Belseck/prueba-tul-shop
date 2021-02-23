package com.tul.shop.web

import com.tul.shop.business.IProductBusiness
import com.tul.shop.exception.BusinessException
import com.tul.shop.exception.NotFoundException
import com.tul.shop.model.Product
import com.tul.shop.util.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PRODUCT)
class ProductRestController {

    @Autowired
    val productBusiness: IProductBusiness? = null

    @GetMapping(Constants.URL_WITHOUT_ID)
    fun list(): ResponseEntity<Any>{
        return try{
            ResponseEntity(productBusiness!!.list(),HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping(Constants.URL_WITH_ID)
    fun load(@PathVariable("id") id:Int): ResponseEntity<Any>{
        return try{
            ResponseEntity(productBusiness!!.find(id),HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping(Constants.URL_WITHOUT_ID)
    fun insert(@RequestBody p: Product): ResponseEntity<Any> {
        return try{
            productBusiness!!.save(p)
            val rh = HttpHeaders()
            rh.set("location",Constants.URL_BASE_PRODUCT+"/"+p.id)
            ResponseEntity(rh,HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping(Constants.URL_WITHOUT_ID)
    fun update(@RequestBody p: Product): ResponseEntity<Any> {
        return try{
            productBusiness!!.save(p)
            val rh = HttpHeaders()
            rh.set("location",Constants.URL_BASE_PRODUCT+"/"+p.id)
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
            productBusiness!!.delete(id)
            val rh = HttpHeaders()
            rh.set("location",Constants.URL_BASE_PRODUCT)
            ResponseEntity(rh,HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
