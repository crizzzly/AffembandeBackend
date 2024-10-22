plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.google.devtools.ksp") version "1.9.25-1.0.20"
}

group = "com.affenbande"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

// TODO: check which really needed
dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-freemarker")
	implementation("org.springframework.boot:spring-boot-starter-jdbc:2.7.0")
	implementation("org.springframework.boot:spring-boot-starter-logging")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA with Hibernate
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
	constraints {
       implementation("org.springdoc:springdoc-openapi-ui:1.6.0") {
           because("To ensure OpenApiCustomiser is available")
       }
   }
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0") // Jakarta Persistence API
	implementation("org.postgresql:postgresql:42.2.20")
	implementation("com.twelvemonkeys.imageio:imageio-webp:3.11.0")
	implementation("org.mockito.kotlin:mockito-kotlin:5.4.0")
	implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")
//	testImplementation("com.h2database:h2")
	testImplementation("io.mockk:mockk:1.13.5")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
