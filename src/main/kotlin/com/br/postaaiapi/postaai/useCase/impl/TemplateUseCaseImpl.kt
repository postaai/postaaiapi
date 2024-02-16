package com.br.postaaiapi.postaai.useCase.impl

import com.br.postaaiapi.postaai.entity.TemplateEntity
import com.br.postaaiapi.postaai.gateway.S3Gateway
import com.br.postaaiapi.postaai.service.TemplateService
import com.br.postaaiapi.postaai.service.bussinessModel.TemplateBusinessInput
import com.br.postaaiapi.postaai.service.bussinessModel.TemplateBusinessOutput
import com.br.postaaiapi.postaai.service.converter.TemplateServiceConverter
import com.br.postaaiapi.postaai.useCase.TemplateUseCase
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Service
class TemplateUseCaseImpl(
    private val templateService: TemplateService,
    private val s3Gateway: S3Gateway,
    private val templateServiceConverter: TemplateServiceConverter
) : TemplateUseCase {

    @Value("\${s3.bucket-template}")
    private val bucketTemplate: String? = null
    override fun saveTemplate(template: TemplateBusinessInput): TemplateBusinessOutput {

        val templateEntity = TemplateEntity(
            name = template.name,
            description = template.description,
            fields = template.fields,
            price = template.price,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
            )

        return templateServiceConverter.toBusinessOutput(templateService.saveTemplate(templateEntity))

    }

    override fun savePack(file: MultipartFile, templateID: String) {
        val template = templateService.findTemplateById(templateID)
        val packURI = s3Gateway.uploadObject(bucketTemplate, file, templateID, templateID)
        template.uri = packURI.toString()
        template.updatedAt = LocalDateTime.now()
        templateService.saveTemplate(template)
    }

    override fun saveImagesExample(file: List<MultipartFile>, templateID: String) {
        val template = templateService.findTemplateById(templateID)
        val imagesURI = file.mapIndexed { index, multipartFile ->
            s3Gateway.uploadImage(bucketTemplate, multipartFile, index.toString(), "$templateID/exampleImages")
        }
        template.exampleImages = imagesURI.map { it.toString() }
        template.updatedAt = LocalDateTime.now()
        templateService.saveTemplate(template)
    }

    override fun findAllTemplates(pageable: Pageable): Page<TemplateBusinessOutput> {
        return templateService.findAllTemplates(pageable).map(templateServiceConverter::toBusinessOutput)
    }

    override fun findById(id: String): TemplateBusinessOutput {
        return templateService.findTemplateById(id).let(templateServiceConverter::toBusinessOutput)
    }
}