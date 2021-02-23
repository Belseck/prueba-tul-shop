package com.tul.shop.service

import com.tul.shop.business.IProductBusiness
import com.tul.shop.dao.ProductRepository
import com.tul.shop.exception.BusinessException
import com.tul.shop.exception.NotFoundException
import com.tul.shop.model.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService: IProductBusiness {

    @Autowired
    val productRepository: ProductRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Product> {
        try {
            return productRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun find(id: Int): Product {
        val op: Optional<Product>
        try {
            op = productRepository!!.findById(id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent)
            throw NotFoundException("Registro no encontrado")

        return op.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun save(p: Product): Product {
        try {
            return productRepository!!.save(p)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun delete(id: Int) {
        val op: Optional<Product>
        try {
            op = productRepository!!.findById(id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent)
            throw NotFoundException("Registro no encontrado")
        else
            try {
                productRepository!!.deleteById(id)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
    }
}