package com.br.postaaiapi.postaai.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/test")
class ControllerTest {


    @GetMapping
    fun findById(@PathVariable id: UUID?): ResponseEntity<String> {
        return ResponseEntity.ok("funcionou.....")
    }

}