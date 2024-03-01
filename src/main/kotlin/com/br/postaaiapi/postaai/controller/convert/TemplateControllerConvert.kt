package com.br.postaaiapi.postaai.controller.convert

import com.br.postaaiapi.postaai.controller.models.TemplateAllResponse
import com.br.postaaiapi.postaai.controller.models.TemplateRequest
import com.br.postaaiapi.postaai.controller.models.TemplateResponse
import com.br.postaaiapi.postaai.service.bussinessModel.TemplateBusinessInput
import com.br.postaaiapi.postaai.service.bussinessModel.TemplateBusinessOutput
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
interface TemplateControllerConvert {

    fun toBusiness(templateRequest: TemplateRequest): TemplateBusinessInput

    fun toResponse(templateBusinessOutput: TemplateBusinessOutput): TemplateResponse

    fun toResponseAll(templateBusinessOutput: TemplateBusinessOutput): TemplateAllResponse
}