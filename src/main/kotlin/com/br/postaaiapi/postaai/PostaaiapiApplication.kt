package com.br.postaaiapi.postaai

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableRabbit
class PostaaiapiApplication

fun main(args: Array<String>) {
	runApplication<PostaaiapiApplication>(*args)
}
