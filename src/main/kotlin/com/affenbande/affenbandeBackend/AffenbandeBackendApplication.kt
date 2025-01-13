package com.affenbande.affenbandeBackend

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import java.util.*


@SpringBootApplication
@EntityScan(basePackages = ["com.affenbande.affenbandeBackend.model"])
class AffenbandeBackendApplication{

	@Bean
	fun commandLineRunner(ctx: ApplicationContext): CommandLineRunner {
		return CommandLineRunner {
			println("Let's inspect the beans provided by Spring Boot:")
			val beanNames: Array<String> = ctx.beanDefinitionNames
			Arrays.sort(beanNames)
			for (beanName in beanNames) {
				println(beanName)
			}
		}
	}
}
fun main(args: Array<String>) {
	val dotenv = Dotenv.configure().filename(".env").load()


	// Set system properties for DB_USER and DB_PASSWORD
	System.setProperty("DB_USER", dotenv["DB_USER"])
	System.setProperty("DB_PASSWD", dotenv["DB_PASSWD"])
	System.setProperty("DB_URL_SHORT", dotenv["DB_URL_SHORT"])
	System.setProperty("DB_NAME", dotenv["DB_NAME"])
	System.setProperty("DB_HOST", dotenv["DB_HOST"])
	System.setProperty("DB_PORT", dotenv["DB_PORT"])

	SpringApplication.run(AffenbandeBackendApplication::class.java, *args)
}

