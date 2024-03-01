package com.br.postaaiapi.postaai.controller.impl

import com.br.postaaiapi.postaai.controller.TemplateController
import com.br.postaaiapi.postaai.controller.convert.TemplateControllerConvert
import com.br.postaaiapi.postaai.controller.models.TemplateAllResponse
import com.br.postaaiapi.postaai.controller.models.TemplateRequest
import com.br.postaaiapi.postaai.controller.models.TemplateResponse
import com.br.postaaiapi.postaai.useCase.TemplateUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/template")
class TemplateControllerImpl(
    private val templateUseCase: TemplateUseCase,
    private val templateControllerConvert: TemplateControllerConvert
): TemplateController {
    override fun getAllTemplates(pageable: Pageable): ResponseEntity<Page<TemplateAllResponse>> {
        val templates = templateUseCase.findAllTemplates(pageable)
        return ResponseEntity.ok(templates.map(templateControllerConvert::toResponseAll))
    }

    override fun createTemplate(templateRequest: TemplateRequest): ResponseEntity<TemplateResponse> {
        val template = templateUseCase.saveTemplate(templateControllerConvert.toBusiness(templateRequest))
        val uri =
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(template.id)
                .toUri()

        return ResponseEntity.created(uri).body(templateControllerConvert.toResponse(template))
    }

    override fun insertExampleImage(file: List<MultipartFile>, templateID: String): ResponseEntity<Any> {
        templateUseCase.saveImagesExample(file, templateID)
        val uri =
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(templateID)
                .toUri()
        return ResponseEntity.created(uri).build()
    }

    override fun getTemplateById(templateID: String): ResponseEntity<TemplateResponse> {
        val template = templateUseCase.findById(templateID)
        return ResponseEntity.ok(templateControllerConvert.toResponse(template))
    }

    override fun insertUri(uri: String, templateID: String): ResponseEntity<Any> {
        templateUseCase.insertUri(uri, templateID)
        val uri =
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(templateID)
                .toUri()
        return ResponseEntity.created(uri).build()
    }
}