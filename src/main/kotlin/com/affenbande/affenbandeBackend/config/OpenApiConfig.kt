package com.affenbande.affenbandeBackend.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.customizers.OpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenAPI(): OpenAPI? {
        return OpenAPI()
            .info(Info().title(API_TITLE).version(API_VERSION))
    }
    @Bean
fun openApiCustomiser(): OpenApiCustomizer {
    return OpenApiCustomizer { openApi ->
        openApi.components.schemas.values.forEach { schema ->
            // Add null check here
            schema.properties?.let { properties ->
                properties.values.forEach { property ->
                    if (property.type == "string") {
                        property.description = property.description ?: API_DESCRIPTION
                    }
                }
            }
        }
    }
}
}