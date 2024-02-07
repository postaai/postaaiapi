package com.br.postaaiapi.postaai.controller

import com.br.postaaiapi.postaai.controller.models.TemplateRequest
import com.br.postaaiapi.postaai.controller.models.TemplateResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@Tag(name = "Template", description = "Template API")
interface TemplateController {

    @Operation(summary = "busca todos os templates", description = "busca todos os templates disponíveis para criação de pacotes")
    @GetMapping("/get-all-template")
    fun getAllTemplates(pageable: Pageable): ResponseEntity<Page<TemplateResponse>>

    @Operation(summary = "cria um template", description = "Cria um template para criação de pacotes")
    @PostMapping("/create-template")
    fun createTemplate(@RequestBody @Valid templateRequest: TemplateRequest): ResponseEntity<TemplateResponse>

    @Operation(summary = "insere um pacote de template", description = "Insere um pacote de template para criação de pacotes")
    @PostMapping("/insert-template-pack")
    fun insertTemplatePack(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("templateID") templateID: String
    ): ResponseEntity<Any>

    @Operation(summary = "insere varias imagem de exemplo", description = "Insere varias imagem de exemplo para exibição")
    @PostMapping("/insert-example-image")
    fun insertExampleImage(
        @RequestParam("file") file: List<MultipartFile>,
        @RequestParam("templateID") templateID: String
    ): ResponseEntity<Any>

    @Operation(summary = "busca template", description = "busca template por id")
    @GetMapping("/get-template/{templateID}")
    fun getTemplateById(templateID: String): ResponseEntity<TemplateResponse>
}