package com.br.postaaiapi.postaai.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMQConfig {

    @Value("\${spring.rabbitmq.queues.send-order-queue}")
    private val orderQueue: String? = null

    @Bean
    fun sendOrderQueue(): Queue {
        return Queue(orderQueue, true)
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange("amq.direct")
    }

    @Bean
    fun binding(): Binding{
        return BindingBuilder
            .bind(sendOrderQueue())
            .to(exchange())
            .with("order_key")
    }

    @Bean
    fun messageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate{
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = messageConverter()

        return rabbitTemplate
    }
}