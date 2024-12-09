//package com.affenbande.affenbandeBackend.controller
//
//import com.affenbande.affenbandeBackend.dao.ImagePathDao
//import com.affenbande.affenbandeBackend.dao.MoveDao
//import com.affenbande.affenbandeBackend.dao.SportDao
//import com.affenbande.affenbandeBackend.dao.SubcategoryDao
//import com.affenbande.affenbandeBackend.dto.SportRequestDTO
//import com.affenbande.affenbandeBackend.model.ImagePath
//import com.affenbande.affenbandeBackend.model.Move
//import com.affenbande.affenbandeBackend.model.Sport
//import com.affenbande.affenbandeBackend.model.Subcategory
//import io.mockk.*
//import io.mockk.impl.annotations.InjectMockKs
//import io.mockk.impl.annotations.MockK
//import io.mockk.junit5.MockKExtension
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.http.HttpStatus
//import java.util.*
//
//@ExtendWith(MockKExtension::class)
//class SportControllerTest {
//
//    @InjectMockKs
//    private lateinit var sportController: SportController
//
//    @MockK
//    private lateinit var sportDao: SportDao
//
//    @MockK
//    private lateinit var moveDao: MoveDao
//
//    @MockK
//    private lateinit var imagePathDao: ImagePathDao
//
//    @MockK
//    private lateinit var subcategoryDao: SubcategoryDao
//
//    private val expectedImagePath = ImagePath().apply {
//        id = 2
//        name = "uploads/sports/rolle_vorwaerts.png"
//    }
//
//    private val expectedSport = Sport().apply {
//        id = 1
//        name = "Tricking"
//        image = expectedImagePath
//        subcategories = emptyList<Subcategory>()
//        moves = emptyList<Move>()
//    }
//
//    private val testSport = Sport().apply {
//        name = "Test Sport"
//        image = expectedImagePath
//        subcategories = emptyList<Subcategory>()
//        moves = emptyList<Move>()
//    }
//
//    @BeforeEach
//    fun setUp() {
//        MockKAnnotations.init(this)
//    }
//
//    @Test
//    fun `addSport should add a new sport`() {
//        // Given
//        val sportRequestDTO = SportRequestDTO(
//            name = "Test Sport",
//            subcategories = emptyList<String>(), // listOf("Subcategory 1", "Subcategory 2"),
//            moves = emptyList<String>(), //listOf("Move 1", "Move 2"),
//            imagePathId = expectedImagePath.id
//                                             )
//
//        every { sportDao.add(any()) } returns Unit
//        every { imagePathDao.findById(any()) } returns Optional.of(expectedImagePath) // Mock findById
//
//        // When
//        val result = sportController.addSport(sportRequestDTO)
//
//        // Then
//        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
//        verify(exactly = 1) { sportDao.add(any()) }
//        verify(exactly = 1) { imagePathDao.findById(any()) }
//    }
//
//    @Test
//    fun `getAllSports should return all sports`() {
//
//        every { sportDao.findAll() } returns listOf<Sport>()
//
//        // When
//        val result = sportController.getAllSports()
//        // Then
//        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
//        verify(exactly = 1) { sportDao.findAll() }
//    }
//
//    @Test
//    fun `getSportById should return the sport with the given ID`() {
//        // Given
//        val sportId = 1
//        val expectedSport = Optional.of(expectedSport)
//        every { sportDao.findById(sportId) } returns expectedSport
//
//        // When
//        val result = sportController.getSportById(sportId)
//
//        // Then
//        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
////        Assertions.assertEquals(expectedSport, result.body!!.get())
//        verify(exactly = 1) { sportDao.findById(sportId) }
//    }
//
//    @Test
//    fun `getSportByName should return the sport with the given name`() {
//        // Given
//        val sportName = "Tricking"
//        val expectedSport = Sport()
//        expectedSport.name = sportName
////        expectedSport.image = imagePathDao.findById(2).get()
//        every { sportDao.findByNameOrNull(sportName) } returns expectedSport
//
//        // When
//        val result = sportController.getSportByName(sportName)
//
//        // Then
//        Assertions.assertEquals(sportName, result.body!!.name)
//        verify(exactly = 1) { sportDao.findByNameOrNull(sportName) }
//    }
//}