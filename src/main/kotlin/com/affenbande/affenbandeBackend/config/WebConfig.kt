package com.affenbande.affenbandeBackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


// TODO: For production, be specific with allowed origins to enhance security.
@Configuration
class WebConfig {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            val host = System.getenv("DB_HOST")
            val port = System.getenv("DB_PORT")
            val url = "http://${host}:${port}"
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**") // Adjust the path as needed
                    .allowedOrigins("*") // Replace with your frontend's URL
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
            }
        }
    }
}