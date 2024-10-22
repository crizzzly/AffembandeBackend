package com.affenbande.affenbandeBackend.controller.old//package com.affenbande.affenbandeBackend
//
//import com.affenbande.affenbandeBackend.dao.ImagePathDao
//import com.affenbande.affenbandeBackend.dto.SportRequest
//import com.affenbande.affenbandeBackend.model.ImagePath
//import com.affenbande.affenbandeBackend.model.Sport
//import com.fasterxml.jackson.databind.ObjectMapper
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.MediaType
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers
//import org.springframework.transaction.annotation.Transactional
//
//@SpringBootTest
//@ActiveProfiles("test")
//@AutoConfigureMockMvc
//@Transactional // Reset database between tests
//class SportControllerIntegrationTest {
//
//    @Autowired
//    lateinit var mockMvc: MockMvc
//
//    @Autowired
//    lateinit var imagePathDao: ImagePathDao
//
//    @Autowired
//    lateinit var objectMapper: ObjectMapper
//
//    lateinit var sampleImagePath: ImagePath
//
//    @BeforeEach
//    fun setUp() {
//        // Insert a mock ImagePath into the database
//        sampleImagePath = ImagePath(null, "/path/to/mock/image")
//        imagePathDao.add(sampleImagePath)
//    }
//
//    @Test
//    fun `should add a new sport`() {
//        val sportRequest = SportRequest(
//            name = "Basketball",
//            imagePathId = sampleImagePath.id,
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
//            .andExpect(MockMvcResultMatchers.jsonPath("$.image.imagePath").value("/path/to/mock/image"))
//    }
//
//    @Test
//    fun `should get sport by id`() {
//        // Create and save a sport to the database
//        val sport = Sport(null, "Soccer", sampleImagePath, null, null)
//        mockMvc.perform(
//            MockMvcRequestBuilders.post("/sports/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(SportRequest("Soccer", listOf(), listOf(), sampleImagePath.id)))
//        ).andReturn()
//
//        // Get the saved sport by its ID
//        val savedSportId = 1  // Assuming it's the first inserted record
//        mockMvc.perform(
//            MockMvcRequestBuilders.get("/sports/get-by-id")
//                .param("id", savedSportId.toString())
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Soccer"))
//    }
//
//    @Test
//    fun `should retrieve all sports`() {
//        // Add two sports to the database
//        val sport1 = SportRequest("Tennis", listOf(), listOf(), sampleImagePath.id)
//        val sport2 = SportRequest("Badminton", listOf(), listOf(), sampleImagePath.id)
//
//        mockMvc.perform(
//            MockMvcRequestBuilders.post("/sports/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(sport1))
//        ).andReturn()
//
//        mockMvc.perform(
//            MockMvcRequestBuilders.post("/sports/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(sport2))
//        ).andReturn()
//
//        // Fetch all sports
//        mockMvc.perform(
//            MockMvcRequestBuilders.get("/sports/get-all")
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
//            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Tennis"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Badminton"))
//    }
//
//    @Test
//    fun `should return 404 when sport is not found by name`() {
//        mockMvc.perform(
//            MockMvcRequestBuilders.get("/sports/get-by-name")
//                .param("name", "NonExistingSport")
//        )
//            .andExpect(MockMvcResultMatchers.status().isNotFound)
//    }
//}
