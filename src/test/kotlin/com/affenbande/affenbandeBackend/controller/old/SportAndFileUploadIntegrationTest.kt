package com.affenbande.affenbandeBackend.controller.old//package com.affenbande.affenbandeBackend
//
//import com.affenbande.affenbandeBackend.dto.SportRequest
//import com.fasterxml.jackson.databind.ObjectMapper
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.MediaType
//import org.springframework.mock.web.MockMultipartFile
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers
//import kotlin.properties.Delegates
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class SportAndFileUploadIntegrationTest {
//
//    @Autowired
//    lateinit var mockMvc: MockMvc
//
//    @Autowired
//    lateinit var objectMapper: ObjectMapper
//
//    var uploadedImageId by Delegates.notNull<Long>()
//
//    @BeforeEach
//    fun setUp() {
//        // Upload an image to get an image path ID
//        val mockImage = MockMultipartFile(
//            "image_file",
//            "mock_image.png",
//            "image/png",
//            byteArrayOf(1, 2, 3) // Fake content
//        )
//        val result = mockMvc.perform(
//            MockMvcRequestBuilders.multipart("/uploads/exampleFiles")
//                .file(mockImage)
//                .param("model", "sport")
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andReturn()
//
//        // Save the uploaded image's ID
//        uploadedImageId = result.response.contentAsString.toLong()
//    }
//
//    @Test
//    fun `should add a new sport with uploaded image`() {
//        // Create a sport with the uploaded image ID
//        val sportRequest = SportRequest(
//            name = "Basketball",
//            imagePathId = uploadedImageId.toInt(),
//            subcategories = listOf(),
//            moves = listOf()
//        )
//
//        mockMvc.perform(
//            MockMvcRequestBuilders.post("/sports/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(sportRequest))
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Basketball"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.image.imagePath").value("/sport/mock_image.png"))
//    }
//}
