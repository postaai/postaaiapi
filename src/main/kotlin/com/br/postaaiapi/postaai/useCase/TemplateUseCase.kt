package com.br.postaaiapi.postaai.useCase

import com.br.postaaiapi.postaai.service.bussinessModel.TemplateBusinessInput
import com.br.postaaiapi.postaai.service.bussinessModel.TemplateBusinessOutput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage

interface TemplateUseCase {

    fun saveTemplate(template: TemplateBusinessInput): TemplateBusinessOutput

    fun saveImagesExample(file: List<MultipartFile>, templateID: String)

    fun resizeImageThumbnails(file: MultipartFile): BufferedImage

    fun findAllTemplates(pageable: Pageable): Page<TemplateBusinessOutput>

    fun insertUri(uri: String, templateID: String)

    fun findById(id: String): TemplateBusinessOutput
}