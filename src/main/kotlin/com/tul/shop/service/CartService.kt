package com.tul.shop.service

import com.tul.shop.business.ICartBusiness
import com.tul.shop.dao.CartRepository
import com.tul.shop.exception.BusinessException
import com.tul.shop.exception.NotFoundException
import com.tul.shop.model.Cart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CartService: ICartBusiness {

    @Autowired
    val cartRepository: CartRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Cart> {
        try {
            return cartRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun find(id: Int): Cart {
        val op: Optional<Cart>
        try {
            op = cartRepository!!.findById(id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent)
            throw NotFoundException("Registro no encontrado")

        return op.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun save(c: Cart): Cart {
        try {
            return cartRepository!!.save(c)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun delete(id: Int) {
        val op: Optional<Cart>
        try {
            op = cartRepository!!.findById(id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent)
            throw NotFoundException("Registro no encontrado")
        else
            try {
                cartRepository!!.deleteById(id)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
    }
}