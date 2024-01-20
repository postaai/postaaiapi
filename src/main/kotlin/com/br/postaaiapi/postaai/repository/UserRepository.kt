package com.br.postaaiapi.postaai.repository

import com.br.postaaiapi.postaai.entity.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<UserEntity, String>