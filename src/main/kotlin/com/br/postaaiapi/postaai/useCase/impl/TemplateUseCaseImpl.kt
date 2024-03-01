package com.br.postaaiapi.postaai.useCase.impl

import com.br.postaaiapi.postaai.entity.TemplateEntity
import com.br.postaaiapi.postaai.enum.EnumCategory
import com.br.postaaiapi.postaai.exception.FileException
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
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.time.LocalDateTime
import javax.imageio.ImageIO

@Service
class TemplateUseCaseImpl(
    private val templateService: TemplateService,
    private val s3Gateway: S3Gateway,
    private val templateServiceConverter: TemplateServiceConverter
) : TemplateUseCase {

    @Value("\${s3.bucket-template}")
    private val bucketTemplate: String? = null

    @Value("\${thumbnail.width}")
    private val scaledWidth: Int = 400

    @Value("\${thumbnail.height}")
    private val scaledHeight: Int = 400

    override fun saveTemplate(template: TemplateBusinessInput): TemplateBusinessOutput {

        val templateEntity = TemplateEntity(
            name = template.name,
            description = template.description,
            fields = template.fields,
            price = template.price,
            category = EnumCategory.valueOf(template.category).getDescription(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
            )

        return templateServiceConverter.toBusinessOutput(templateService.saveTemplate(templateEntity))

    }

    override fun saveImagesExample(file: List<MultipartFile>, templateID: String) {
        val template = templateService.findTemplateById(templateID)
        val imagesURI = file.mapIndexed { index, multipartFile ->
            s3Gateway.uploadImage(bucketTemplate, multipartFile, index.toString(), "$templateID/exampleImages")
        }

        val thumbnails = file.map { resizeImageThumbnails(it) }
        template.exampleImages = imagesURI.map { it.toString() }
        template.updatedAt = LocalDateTime.now()
        template.exampleThumbnails = thumbnails.mapIndexed { index, bufferedImage ->
            s3Gateway.uploadImage(bucketTemplate, bufferedImage, index.toString(), "$templateID/exampleThumbnails").toString()
        }
        templateService.saveTemplate(template)
    }

    override fun resizeImageThumbnails(file: MultipartFile): BufferedImage{

        try {
            val originalImage = ImageIO.read(ByteArrayInputStream(file.bytes))
            return resizeImage(originalImage)
        } catch (e: Exception) {
            throw FileException().errorToReadFile()
        }
    }

   private fun resizeImage(image: BufferedImage): BufferedImage {
        val resizedImage = BufferedImage(scaledWidth, scaledHeight, image.type)
        val graphics2D = resizedImage.createGraphics()
        graphics2D.drawImage(
            image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH), 0, 0, scaledWidth, scaledHeight, null)
        graphics2D.dispose()
        return resizedImage
    }

    override fun findAllTemplates(pageable: Pageable): Page<TemplateBusinessOutput> {
        return templateService.findAllTemplates(pageable).map(templateServiceConverter::toBusinessOutput)
    }

    override fun insertUri(uri: String, templateID: String) {
        val template = templateService.findTemplateById(templateID)
        template.uri = uri
        templateService.saveTemplate(template)
    }

    override fun findById(id: String): TemplateBusinessOutput {
        return templateService.findTemplateById(id).let(templateServiceConverter::toBusinessOutput)
    }
}