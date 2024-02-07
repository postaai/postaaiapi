package com.br.postaaiapi.postaai.useCase

import com.br.postaaiapi.postaai.service.bussinessModel.TemplateBusinessInput
import com.br.postaaiapi.postaai.service.bussinessModel.TemplateBusinessOutput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile

interface TemplateUseCase {

    fun saveTemplate(template: TemplateBusinessInput): TemplateBusinessOutput

    fun savePack(file: MultipartFile, templateID: String)

    fun saveImagesExample(file: List<MultipartFile>, templateID: String)

    fun findAllTemplates(pageable: Pageable): Page<TemplateBusinessOutput>

    fun findById(id: String): TemplateBusinessOutput
}