package com.br.postaaiapi.postaai.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfiguration : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*") // Aqui você pode especificar os domínios permitidos
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
            .allowedHeaders("*") // Headers permitidos
            .allowCredentials(true) // Permite credenciais (por exemplo, cookies)
            .maxAge(3600) // Tempo de cache para pré-flush do preflight request
    }
}
