package com.affenbande.affenbandeBackend.services

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.SubcategoryRequestDTO
import com.affenbande.affenbandeBackend.model.ImagePath
import com.affenbande.affenbandeBackend.model.Move
import com.affenbande.affenbandeBackend.model.Sport
import com.affenbande.affenbandeBackend.model.Subcategory
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.test.util.AssertionErrors.assertEquals
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class SubcategoryServiceTest {

    @InjectMockKs
    private lateinit var subcategoryService: SubcategoryService

    @MockK
    private lateinit var subcategoryDao: SubcategoryDao

    @MockK
    private lateinit var sportDao: SportDao

    @MockK
    private lateinit var moveDao: MoveDao

    @MockK
    private lateinit var imagePathDao: ImagePathDao


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // Initialize mocks
//        Initialize MockK annotations
        subcategoryService.sportDao = sportDao // Inject mocked sportDao
        subcategoryService.moveDao = moveDao
        subcategoryService.imagePathDao = imagePathDao
        subcategoryService.subcategoryDao = subcategoryDao

    }

    @Test
    fun `addSubcategory should add a new subcategory`() {
        val subcategoryRequestDTO = SubcategoryRequestDTO(
            name = "Test Subcategory",
            imagePathId = 2,
            sports = listOf("1", "2"),
            moves = listOf("1", "2")
        )
        val expectedImagePath = ImagePath(2, "testpath")
        val mockSubcategory = mockk<Subcategory>()
        every { mockSubcategory.toResponseDTO() } returns mockk()

        every { imagePathDao.findByIdOrNull(any()) } returns expectedImagePath
        every { sportDao.findByIdOrNull(any()) } returns Sport()
        every { moveDao.findByIdOrNull(any()) } returns Move()
        every { subcategoryDao.add(any()) } returns Unit


        // When
        subcategoryService.addSubcategory(subcategoryRequestDTO)
        // Then
        verify { imagePathDao.findByIdOrNull(2) }
        verify(exactly = 1) { sportDao.findByIdOrNull(1) }
        verify(exactly = 1) { sportDao.findByIdOrNull(2) }
        verify(exactly = 1) { moveDao.findByIdOrNull(1) }
        verify(exactly = 1) { moveDao.findByIdOrNull(2) }
        verify { subcategoryDao.add(any()) }
    }


    @Test
    fun `updateSubcategory should update the name`() {
        every { subcategoryDao.findById(any()) } returns Optional.of(Subcategory())
        every { subcategoryDao.update(any()) } returns Subcategory() // Or capture the argument and assert

        val result = subcategoryService.updateSubcategory(1, "New Name")

        verify { subcategoryDao.update(match { it.name == "New Name" }) } // Verify update() call with correct name
        assertEquals("New Name", result.name) // Assert updated name in DTO
    }


    @Test
    fun `getSubcategoriesBySportId should return an empty List when there are no related Subcategories for a Sport ID`() {
        val subcatIds: List<Int?> = listOf() // Empty list of Ids.
        val sportId = "1"
        every { subcategoryDao.getSubcategoryIdsByRelatedSportId(sportId.toInt()) } returns mutableListOf<Any?>()

        val result = subcategoryService.getSubcategoriesBySportId(sportId)

        verify { subcategoryDao.getSubcategoryIdsByRelatedSportId(sportId.toInt()) }
        assertEquals(0, result.size) // Empty list returned when no subcategories associated with sportId

    }



    @Test
    fun `getSubcategoryById should throw exception when no Subcategory is found`() {
        every { subcategoryDao.findById(999) } returns Optional.empty() // Simulate no result

        assertThrows<NoSuchElementException> {
            subcategoryService.getSubcategoryById(999) // Expecting an exception
        }

        verify { subcategoryDao.findById(999) }
        confirmVerified(subcategoryDao)
    }


    @Test
    fun `getSubcategory by id should return a subcategory`() {
        every { subcategoryDao.findById(1) } returns Optional.of(Subcategory())

        subcategoryService.getSubcategoryById(1)

        verify { subcategoryDao.findById(1) }
        confirmVerified(subcategoryDao)
    }

}