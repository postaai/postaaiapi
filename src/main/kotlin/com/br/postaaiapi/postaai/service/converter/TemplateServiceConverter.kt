package com.br.postaaiapi.postaai.service.converter

import com.br.postaaiapi.postaai.entity.TemplateEntity
import com.br.postaaiapi.postaai.service.bussinessModel.TemplateBusinessOutput
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
interface TemplateServiceConverter {

    fun toBusinessOutput(templateEntity: TemplateEntity): TemplateBusinessOutput

    fun toEntity(template: TemplateBusinessOutput): TemplateEntity
}