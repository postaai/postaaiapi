package com.br.postaaiapi.postaai.useCase

import com.br.postaaiapi.postaai.service.bussinessModel.OrderMessageProcessedInput

interface OrderSaveResultUseCase {

    fun saveResultOrder(order: OrderMessageProcessedInput)

}