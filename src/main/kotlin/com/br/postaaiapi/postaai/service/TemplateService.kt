package com.br.postaaiapi.postaai.service

import com.br.postaaiapi.postaai.entity.TemplateEntity
import com.br.postaaiapi.postaai.service.bussinessModel.TemplateBusinessOutput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TemplateService {

    fun saveTemplate(template: TemplateEntity): TemplateEntity

    fun findTemplateById(id: String): TemplateEntity

    fun findAllTemplates(pageable: Pageable): Page<TemplateEntity>
}