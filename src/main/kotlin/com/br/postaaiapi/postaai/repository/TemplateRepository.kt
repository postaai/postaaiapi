package com.br.postaaiapi.postaai.repository

import com.br.postaaiapi.postaai.entity.TemplateEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TemplateRepository : MongoRepository<TemplateEntity, String> {

}