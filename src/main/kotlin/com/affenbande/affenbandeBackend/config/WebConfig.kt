package com.affenbande.affenbandeBackend.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


// TODO: For production, be specific with allowed origins to enhance security.
/* If your mobile app connects directly to a fixed backend URL, you could configure a more permissive allowedOrigins setting only during development. For example:
.allowedOrigins("http://localhost:[PORT]", "http://10.0.2.2:[PORT]") // Android emulator
// Add other development URLs as needed

Production: Always use a proxy server (Nginx, Apache) for production. This isolates your backend and allows for proper CORS configuration.
Development (with fixed URL): You can relax allowedOrigins to include common development URLs (localhost, emulator addresses). Be very cautious about using "*" even during development.
Dynamic Backend URL (Discouraged): If the backend URL changes dynamically (not recommended), consider alternative app architectures. If you absolutely must use this pattern, only use "*" during development and evaluate secure alternatives for production.s
* */

@Configuration
class WebConfig {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {

            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**") // Adjust the path as needed
                    .allowedOrigins("*") // use Nginx as proxy for production
                    .allowedMethods("GET", "POST", "DELETE")
                    .allowedHeaders("Content-Type", "Authorization")
//                    .allowedHeaders("*") // Specific headers
                    .maxAge(3600)
                    // .allowCredentials(true) // Only if needed, with CSRF protection
            }
        }
    }

}

// TODO: add restrictions to admin page - only allowed users