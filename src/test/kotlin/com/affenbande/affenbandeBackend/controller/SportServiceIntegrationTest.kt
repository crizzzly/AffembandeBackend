//package com.affenbande.affenbandeBackend.controller
//import com.affenbande.affenbandeBackend.dao.ImagePathDao
//import com.affenbande.affenbandeBackend.model.Sport
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.core.ParameterizedTypeReference
//import org.springframework.http.*
//import org.springframework.http.client.MultipartBodyBuilder
//import org.springframework.mock.web.MockMultipartFile
//import java.io.File
//
//
//fun print_response_to_console(response: ResponseEntity<Sport>) {
//    println("##############################################################")
//    println("##############################################################")
//    println()
//    println("Response Status: ${response.statusCode}")  // Log status
//    println("Response Body: ${response.body}")  // Log the raw response body
//    println()
//    println("##############################################################")
//    println("##############################################################")
//}
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class SportControllerIntegrationTest {
//    var fetchedId: Int? = null
//    @Autowired
//    private lateinit var template: TestRestTemplate
//
//    @MockBean
//    private lateinit var imageSrcDao: ImagePathDao
//
//    val sport = Sport()
////                name = "Football",
////                imageSrc = "football.png",
//    //            subcategoryIds = emptyList(),  // Set to null
//    //            moveIds = emptyList()           // Set to null
//
//    val testImagePath = "uploads/Dog.png"
//    @Test
//    @Throws(Exception::class)
//    fun `addSport should return sport when all parameters are provided`() {
//        val headers = HttpHeaders()
//        headers.set("Content-Type", "application/json")
//
//        // Create the Sport object
//        val sport = Sport()
//        sport.name = exampleSport["name"].toString()
//        val imgFile: File = File(exampleSport["imageSrc"].toString())
//        val imageFileContent = imgFile.readBytes()
//
//        val imageFile = MockMultipartFile(
//            "image_file",
//            "football.png",
//            MediaType.IMAGE_PNG_VALUE,
//            imageFileContent
//        )
//
//        // Create HttpEntity with the MultipartFile and Sport name
//        val bodyBuilder = MultipartBodyBuilder()
//        bodyBuilder.part("name", "football")
//        bodyBuilder.part("image_file", imageFile.resource)
//
//        val requestEntity = HttpEntity(bodyBuilder.build(), headers)
//        // Send POST request
//        val response: ResponseEntity<Sport> = template.postForEntity(
//            "/sports/add",
//            requestEntity,
//            Sport::class.java
//        )
//
//        // Assert the response
//        assertThat(response.statusCode).isEqualTo(HttpStatus.OK) // Expecting HTTP 200
//
//        val actualSport = response.body
//        println("received sport: $actualSport")
//        assertThat(actualSport).isNotNull
////        assertThat(actualSport?.name).isEqualTo(sport.name)
////
////        // Assert that the imageSrc object is not null
////        val imageSrc = actualSport?.image
////        assertThat(imageSrc).isNotNull
//
//        // Verify that the image file paths are correctly stored in ImageSrc
////        assertThat(imageSrc?.xs).endsWith("-xs.webp")
////        assertThat(imageSrc?.s).endsWith("-s.webp")
////        assertThat(imageSrc?.m).endsWith("-m.webp")
////        assertThat(imageSrc?.l).endsWith("-l.webp")
////        assertThat(imageSrc?.xl).endsWith("-xl.webp")
//    }}
//
////    @Test
////    fun `getAllSports should return all sports`() {
////        val headers = HttpHeaders()
////        headers.contentType = MediaType.APPLICATION_JSON  // Set content type to JSON
////
////        val requestEntity = HttpEntity<Any>(headers)   // Create HttpEntity with the request
////        val response: ResponseEntity<List<Sport>> = template.exchange(
////            "/sports/get-all",
////            HttpMethod.GET,
////            requestEntity,
////            object : ParameterizedTypeReference<List<Sport>>() {}
////        )
////
////
////        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
////        val actualSports = response.body
////        assertThat(actualSports).isNotNull
////        assertThat(actualSports?.size).isGreaterThan(0)
////    }
////
////    @Test
////    fun `getSportByName should return sport`() {
////        val headers = HttpHeaders()
////        headers.set("Content-Type", "application/json")
////
////        // Create HttpEntity with the Sport object
////        val name = sport.name
////        headers.set("name", name)
////        val requestEntity = HttpEntity<Any>(headers)
//
////        TODO(
////         An error occurred:
////         Argument 'Football' could not be converted to the
////         identifier type of entity 'com.affenbande.affenbandeBackend.model.Sport' [Error coercing value]
////         )
////        val response: ResponseEntity<Sport> = template.getForEntity(
////            "/sports/get-by-name",  //?name=${name}
////            Sport::class.java,
////            requestEntity,
////            object : ParameterizedTypeReference<Sport>() {}
////        )
////        val response: ResponseEntity<Sport> = template.getForEntity(
////            "/sports/get-by-name?name=${name}",
////            Sport::class.java,
////            requestEntity
////        )
////        print_response_to_console(response)
////        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
////        val actualSport = response.body
////
////        fetchedId = actualSport?.id
////        assertThat(actualSport).isNotNull
////        assertThat(actualSport?.name).isEqualTo(sport.name)
////        assertThat(actualSport?.imagePath).isEqualTo(sport.imagePath)
////        assertThat(actualSport?.subcategoryIds).isEqualTo(sport.subcategoryIds)
////        assertThat(actualSport?.moveIds).isEqualTo(sport.moveIds)
////    }}
//
////    @Test()
////    fun `getSportById should return sport`() { //getAllSports should return all sports
////        val headers = HttpHeaders()
////        headers.set("Content-Type", "application/json")
////        val sportId = fetchedId ?: 4
////        headers.set("id", sportId.toString())
////        // Create HttpEntity with the Sport object
////        val requestEntity = HttpEntity<Any>(headers)
////
////        val response: ResponseEntity<Sport> = template.getForEntity(
////            "/sports/get-by-id?id=${sportId}",
////            Sport::class.java,
////            requestEntity
////        )
////        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
////
////        val actualSport = response.body
////        assertThat(actualSport).isNotNull
////        assertThat(actualSport?.name).isEqualTo(sport.name)
////        assertThat(actualSport?.imageSrc).isEqualTo(sport.imageSrc)
//////        assertThat(actualSport?.subcategoryIds).isEqualTo(sport.subcategoryIds)
//////        assertThat(actualSport?.moveIds).isEqualTo(sport.moveIds)
////    }
////}
