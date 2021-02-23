package com.tul.shop.dao

import com.tul.shop.model.ProductCart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductCartRepository: JpaRepository<ProductCart, Int> {
}