//import com.affenbande.affenbandeBackend.controller.SubcategoryController
//import com.affenbande.affenbandeBackend.dao.ImagePathDao
//import com.affenbande.affenbandeBackend.dao.MoveDao
//import com.affenbande.affenbandeBackend.dao.SportDao
//import com.affenbande.affenbandeBackend.dao.SubcategoryDao
//import com.affenbande.affenbandeBackend.dto.SubcategoryRequestDTO
//import com.affenbande.affenbandeBackend.model.ImagePath
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
//class SubcategoryControllerTest {
//
//    @InjectMockKs
//    private lateinit var subcategoryController: SubcategoryController
//
//    @MockK
//    private lateinit var subcategoryDao: SubcategoryDao
//
//    @MockK
//    private lateinit var imagePathDao: ImagePathDao
//
//    @MockK
//    private lateinit var moveDao: MoveDao
//
//    @MockK
//    private lateinit var sportDao: SportDao
//
//    private val expectedImagePath = ImagePath().apply {
//        id = 2
//        name = "uploads/subcategories/flips.png"
//    }
//
//    private val expectedSubcategory = Subcategory().apply {
//        name = "Flips"
//        image = expectedImagePath
//    }
//
//    private val testSubcategory = Subcategory().apply {
//        name = "Test Subcategory"
//        image = expectedImagePath
//    }
//
//    @BeforeEach
//    fun setUp() {
//        MockKAnnotations.init(this)
//    }
//
//    @Test
//    fun `addSubcategory should add a new subcategory`() {
//        // Given
//        val subcategoryRequestDTO = SubcategoryRequestDTO(
//            name = "Test Subcategory",
//            imagePathId = expectedImagePath.id,
//            sports = emptyList<String>(),
//            moves = emptyList<String>(),
//                                                         )
//
//        every { subcategoryDao.add(any()) } returns Unit
//        every { imagePathDao.findById(any()) } returns Optional.of(expectedImagePath)
//        // When
//        val result = subcategoryController.addSubcategory(subcategoryRequestDTO)
//
//        // Then
//        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
//        verify(exactly = 1) { subcategoryDao.add(any()) }
//        verify(exactly = 1) { imagePathDao.findById(any()) }
//    }
//
//    @Test
//    fun `getAllSubcategories should return all subcategories`() {
//        // Given
//        every { subcategoryDao.findAll() } returns listOf(expectedSubcategory, testSubcategory)
//
//        // When
//        val result = subcategoryController.getAllSubcategories()
//
//        // Then
//        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
//        verify(exactly = 1) { subcategoryDao.findAll() }
//    }
//
//    @Test
//    fun `getSubcategoryById should return the subcategory with the given ID`() {
//        // Given
//        val subcategoryId = 1
//        every { subcategoryDao.findById(subcategoryId) } returns Optional.of(expectedSubcategory)
//
//        // When
//        val result = subcategoryController.getSubcategoryById(subcategoryId)
//
//        // Then
//        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
//        Assertions.assertEquals(expectedSubcategory, result.body!!)
//        verify(exactly = 1) { subcategoryDao.findById(subcategoryId) }
//    }
//
//    @Test
//    fun `getSubcategoryByName should return the subcategory with the given name`() {
//        // Given
//        val subcategoryName = "Flips"
//        every { subcategoryDao.findByNameOrNull(subcategoryName) } returns expectedSubcategory
//
//        // When
//        val result = subcategoryController.getSubcategoryByName(subcategoryName)
//
//        // Then
//        Assertions.assertEquals(subcategoryName, result.body!!.name)
//        verify(exactly = 1) { subcategoryDao.findByNameOrNull(subcategoryName) }
//    }
//}
