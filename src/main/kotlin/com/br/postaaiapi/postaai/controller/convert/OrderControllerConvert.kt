package com.br.postaaiapi.postaai.controller.convert

import com.br.postaaiapi.postaai.controller.models.OrderRequest
import com.br.postaaiapi.postaai.controller.models.OrderResponse
import com.br.postaaiapi.postaai.service.bussinessModel.OrderBusinessInput
import com.br.postaaiapi.postaai.service.bussinessModel.OrderBusinessOutput
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
interface OrderControllerConvert {

    fun toBusiness(orderRequest: OrderRequest): OrderBusinessInput

    fun toResponse(orderBusinessOutput: OrderBusinessOutput): OrderResponse
}


