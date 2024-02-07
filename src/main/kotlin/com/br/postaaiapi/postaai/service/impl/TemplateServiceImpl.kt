package com.br.postaaiapi.postaai.service.impl

import com.br.postaaiapi.postaai.entity.TemplateEntity
import com.br.postaaiapi.postaai.exception.ObjectNotFoundException
import com.br.postaaiapi.postaai.repository.TemplateRepository
import com.br.postaaiapi.postaai.service.TemplateService
import com.br.postaaiapi.postaai.service.bussinessModel.TemplateBusinessOutput
import com.br.postaaiapi.postaai.service.converter.TemplateServiceConverter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TemplateServiceImpl(
    private val templateRepository: TemplateRepository,
    private val templateServiceConverter: TemplateServiceConverter
) : TemplateService {
    override fun saveTemplate(template: TemplateEntity): TemplateBusinessOutput {
        return templateServiceConverter.toBusinessOutput(templateRepository.save(template))
    }

    override fun findTemplateById(id: String): TemplateBusinessOutput {
        return templateRepository.findById(id)
            .orElseThrow { ObjectNotFoundException() }
            .let(templateServiceConverter::toBusinessOutput)
    }

    override fun findAllTemplates(pageable: Pageable): Page<TemplateBusinessOutput> {
        return templateRepository.findAll(pageable).map(templateServiceConverter::toBusinessOutput)
    }
}