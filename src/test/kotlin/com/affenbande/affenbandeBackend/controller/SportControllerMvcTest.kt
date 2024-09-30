//package com.affenbande.affenbandeBackend.controller
//
//import com.affenbande.affenbandeBackend.dao.SportDao
//import com.affenbande.affenbandeBackend.model.Sport
//import org.junit.jupiter.api.Test
//import org.mockito.kotlin.any
//import org.mockito.kotlin.whenever
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.http.MediaType
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers
//
//@WebMvcTest(SportController::class)
//class SportControllerMcvTest {
//
//
//    @Autowired
//    private lateinit var mockMvc: MockMvc
//
//    @MockBean
//    private lateinit var sportDao: SportDao
//
//    @Test
//    fun `addSport should return sport when all parameters are provided`() {
//        // Create a Sport instance with null subcategories and moves
//        val sport = Sport(
////            name = "Football",
////            imageSrc = "football.png",
////            subcategoryIds = emptyList(),  // Set to null
////            moveIds = emptyList()           // Set to null
//        )
//        sport.name = "Football"
//        val imageSrc = "uploads/Dog.png"
//
//
//        // Stubbing the sportDao.add() method to return the mock sport
//        whenever(sportDao.add(any())).thenAnswer {invocation ->
//            // Retrieve the argument passed to the method
//            val sportArg = invocation.getArgument<Sport>(0)
//            sportArg // Return a copy of the sport or just sportArg
//        }
//
//        val request = MockMvcRequestBuilders.post("/sports/add")
//            .param("name", sport.name)
//            .param("imageSrc", imageSrc)
////            .param("subcategoryIds", sport.subcategoryIds.toString()) // Each subcategory as a separate parameter
////            .param("moveIds", sport.integerIds.toString())         // Each move as a separate parameter
//            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//
//        mockMvc.perform(request)
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Football"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.imageSrc").value("football.png"))
////            .andExpect(MockMvcResultMatchers.jsonPath("$.subcategories").isEmpty) // Expecting null or empty
////            .andExpect(MockMvcResultMatchers.jsonPath("$.moves").isEmpty)         // Expecting null or empty
//    }
//}
