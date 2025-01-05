//import com.affenbande.affenbandeBackend.controller.MoveController
//import com.affenbande.affenbandeBackend.dao.ImagePathDao
//import com.affenbande.affenbandeBackend.dao.MoveDao
//import com.affenbande.affenbandeBackend.dao.SportDao
//import com.affenbande.affenbandeBackend.dao.SubcategoryDao
//import com.affenbande.affenbandeBackend.dto.MoveRequest
//import com.affenbande.affenbandeBackend.dto.MoveRequestDTO
//import com.affenbande.affenbandeBackend.model.ImagePath
//import com.affenbande.affenbandeBackend.model.Move
//import com.affenbande.affenbandeBackend.model.Sport
//import com.affenbande.affenbandeBackend.model.Subcategory
//import com.affenbande.affenbandeBackend.services.MoveService
//import io.mockk.MockKAnnotations
//import io.mockk.every
//import io.mockk.impl.annotations.InjectMockKs
//import io.mockk.impl.annotations.MockK
//import io.mockk.junit5.MockKExtension
//import io.mockk.verify
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.http.HttpStatus
//import java.util.*
//
//@ExtendWith(MockKExtension::class)
//class MoveControllerTest {
//
//    @InjectMockKs
//    private lateinit var moveController: MoveController
//
//    @MockK
//    private lateinit var moveService: MoveService
//
//
//    @MockK
//    private lateinit var moveDao: MoveDao
//
//    @MockK
//    private lateinit var imagePathDao: ImagePathDao
//
//
//    @MockK
//    private lateinit var subcategoryDao: SubcategoryDao
//
//    @MockK
//    private lateinit var sportDao: SportDao
//
//
//    private val expectedImagePath = ImagePath().apply {
//        id = 2
//        name = "uploads/moves/flips.png"
//    }
//
//    private val expectedMove = Move().apply {
//        name = "Flips"
//        image = expectedImagePath
//    }
//
//    private val testMove = Move().apply {
//        name = "Test Move"
//        image = expectedImagePath
//    }
//
//    @BeforeEach
//    fun setUp() {
//        MockKAnnotations.init(this)
//    }
//
//    @Test
//    fun `addMove should add a new move`() {
//        // Given
//        val moveRequest = MoveRequestDTO(
//            name = "Test Move",
//            sports = emptyList<String>(),
//            subcategories = emptyList<String>(),
//            isCoreMove = false,
//            level = 0,
//            intensity = 0,
//            frequency = 0,
//            timePreparation = 0,
//            timeExercise = 0,
//            formula = null,
//            preMoves = emptyList<String>(),
//            optPreMoves = emptyList<String>(),
//            description = null,
//        )
//
//        every { moveDao.add(any()) } returns Unit
//        every { subcategoryDao.findByNameOrNull(any()) } returns Subcategory()
//        every { sportDao.findByNameOrNull(any()) } returns Sport()
////        verify { sportDao.findByNameOrNull(any()) }
//
//        // When
//        val result = moveController.addMove(moveRequest)
//
//        // Then
//        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
//        verify(exactly = 1) { moveDao.add(any()) }
////        verify(exactly = 1) { subcategoryDao.findByNameOrNull(any()) }
////        verify(exactly = 1) { sportDao.findByNameOrNull(any()) }
//    }
//
//    @Test
//    fun `getAllMoves should return all moves`() {
//        // Given
//        every { moveDao.findAll() } returns listOf(expectedMove, testMove)
//
//        // When
//        val result = moveController.getAllMoves()
//
//        // Then
//        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
//        verify(exactly = 1) { moveDao.findAll() }
//    }
//
//    @Test
//    fun `getMoveById should return the move with the given ID`() {
//        // Given
//        val moveId = 1
//        every { moveDao.findById(moveId) } returns Optional.of(expectedMove)
//
//        // When
//        val result = moveController.getMoveById(moveId)
//
//        // Then
//        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
////        Assertions.assertEquals(expectedMove, result.body!!.get())
//        verify(exactly = 1) { moveDao.findById(moveId) }
//    }
//
//    @Test
//    fun `getMoveByName should return the move with the given name`() {
//        // Given
//        val moveName = "Flips"
//        every { moveDao.findByNameOrNull(moveName) } returns expectedMove
//
//        // When
//        val result = moveController.getMoveByName(moveName)
//
//        // Then
//        Assertions.assertEquals(moveName, result.body!!.name)
//        verify(exactly = 1) { moveDao.findByNameOrNull(moveName) }
//    }
//}
