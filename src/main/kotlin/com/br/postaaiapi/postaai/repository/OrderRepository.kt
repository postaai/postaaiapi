package com.br.postaaiapi.postaai.repository

import com.br.postaaiapi.postaai.entity.OrderEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: MongoRepository<OrderEntity, String> {
}