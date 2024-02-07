package com.br.postaaiapi.postaai.service.impl

import com.br.postaaiapi.postaai.entity.TemplateEntity
import com.br.postaaiapi.postaai.exception.ObjectNotFoundException
import com.br.postaaiapi.postaai.repository.TemplateRepository
import com.br.postaaiapi.postaai.service.TemplateService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

private const val TEMPLATE_NAO_ENCONTRADO = "Template não encontrado"

@Service
class TemplateServiceImpl(
    private val templateRepository: TemplateRepository,
) : TemplateService {
    override fun saveTemplate(template: TemplateEntity): TemplateEntity {
        return templateRepository.save(template)
    }

    override fun findTemplateById(id: String): TemplateEntity {
        return templateRepository.findById(id)
            .orElseThrow { ObjectNotFoundException(TEMPLATE_NAO_ENCONTRADO) }
    }

    override fun findAllTemplates(pageable: Pageable): Page<TemplateEntity> {
        return templateRepository.findAll(pageable)
    }
}