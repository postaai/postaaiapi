package com.br.postaaiapi.postaai.service.converter

import com.br.postaaiapi.postaai.entity.OrderEntity
import com.br.postaaiapi.postaai.service.bussinessModel.OrderBusinessOutput
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
interface OrderServiceConverter {

    fun toBusinessOutput(orderEntity: OrderEntity): OrderBusinessOutput
    fun toEntity(order: OrderBusinessOutput): OrderEntity


}