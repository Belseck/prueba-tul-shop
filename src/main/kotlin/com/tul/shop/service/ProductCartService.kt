package com.tul.shop.service

import com.tul.shop.business.IProductCartBusiness
import com.tul.shop.dao.ProductCartRepository
import com.tul.shop.exception.BusinessException
import com.tul.shop.exception.NotFoundException
import com.tul.shop.model.ProductCart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductCartService: IProductCartBusiness {

    @Autowired
    val productCartRepository: ProductCartRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<ProductCart> {
        try {
            return productCartRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun find(id: Int): ProductCart {
        val op: Optional<ProductCart>
        try {
            op = productCartRepository!!.findById(id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent)
            throw NotFoundException("Registro no encontrado")

        return op.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun save(p: ProductCart): ProductCart {
        try {
            return productCartRepository!!.save(p)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun delete(id: Int) {
        val op: Optional<ProductCart>
        try {
            op = productCartRepository!!.findById(id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent)
            throw NotFoundException("Registro no encontrado")
        else
            try {
                productCartRepository!!.deleteById(id)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
    }

    @Throws(BusinessException::class)
    override fun byCart(id: Int): List<ProductCart> {
        try {
            return productCartRepository!!.findAll().filter { it.cart.id == id }
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun byProduct(id: Int): List<ProductCart> {
        try {
            return productCartRepository!!.findAll().filter { it.product.id == id }
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
}