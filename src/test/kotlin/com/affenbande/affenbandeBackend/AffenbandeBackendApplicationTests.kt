//package com.affenbande.affenbandeBackend
//
//import org.hamcrest.Matchers.equalTo
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.MediaType
//import org.springframework.http.ResponseEntity
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
//import org.assertj.core.api.Assertions.assertThat
//import org.springframework.boot.test.web.client.TestRestTemplate
//
////@SpringBootTest
////@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class HelloControllerTest {
//	@Autowired
//	private lateinit var template: TestRestTemplate
//	private val asserted_msg1 = "Greetings from Spring Boot!"
//	private val asserted_msg =
//        "[{\"id\":\"1\",\"name\":\"Jallo\"},{\"id\":\"2\",\"name\":\"Bonjour\"},{\"id\":\"3\",\"name\":\"Privet\"}]"
//	//private val mvc: MockMvc? = null
//
////	@Test
////	@Throws(java.lang.Exception::class)
////	fun getHello() {
////		mvc!!.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
////			.andExpect(status().isOk())
////			.andExpect(content().string(equalTo("Greetings from Spring Boot!")))
////	}
//
//	@Test
//	@Throws(Exception::class)
//	fun getHello() {
//		val response: ResponseEntity<String> = template.getForEntity("/", String::class.java)
//		assertThat(response.body).isEqualTo(asserted_msg)
//	}
//}
//
//
////import org.springframework.boot.test.web.client.TestRestTemplate;
