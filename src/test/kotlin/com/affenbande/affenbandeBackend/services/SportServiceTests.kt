package com.affenbande.affenbandeBackend.services

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.SportRequestDTO
import com.affenbande.affenbandeBackend.dto.SportResponseDTO
import com.affenbande.affenbandeBackend.model.ImagePath
import com.affenbande.affenbandeBackend.model.Move
import com.affenbande.affenbandeBackend.model.Sport
import com.affenbande.affenbandeBackend.model.Subcategory
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Test
import java.util.*
import org.junit.jupiter.api.BeforeEach

class SportServiceTest {


    @MockK
    private lateinit var sportDao: SportDao

    @MockK
    private lateinit var moveDao: MoveDao

    @MockK
    private lateinit var imagePathDao: ImagePathDao

    @MockK
    private lateinit var subcategoryDao: SubcategoryDao

    @InjectMockKs
    private lateinit var sportService: SportService // Real service


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // Initialize mocks
//        Initialize MockK annotations
        sportService.sportDao = sportDao // Inject mocked sportDao
        sportService.moveDao = moveDao
        sportService.imagePathDao = imagePathDao
        sportService.subcategoryDao = subcategoryDao
    }


    @Test
    fun `addSport should add a new sport`() {
        // Given
        val sportRequestDTO = SportRequestDTO(
            name = "Test Sport",
            subcategories = listOf("1", "2"),
            moves = listOf("1", "2"),
            imagePathId = 2
        )
        val expectedImagePath = ImagePath(2, "path")
        val mockSport = mockk<Sport>()

        every { mockSport.toResponseDTO() } returns mockk()
        every { imagePathDao.findByIdOrNull(any()) } returns expectedImagePath
        every { subcategoryDao.findByIdOrNull(any()) } returns Subcategory()
        every { moveDao.findByIdOrNull(any()) } returns Move()
        every { sportDao.add(any()) } returns Unit

        // When
        sportService.addSport(sportRequestDTO)

        // Then
        verify { sportDao.add(any()) }
        verify  { imagePathDao.findByIdOrNull(2) }
        verify(exactly = 1) { subcategoryDao.findByIdOrNull(1) }
        verify(exactly = 1) { subcategoryDao.findByIdOrNull(2) }
        verify(exactly = 1) { moveDao.findByIdOrNull(1) }
        verify(exactly = 1) { moveDao.findByIdOrNull(2) }
        confirmVerified(sportDao, imagePathDao, subcategoryDao, moveDao)

    }

    // ... other test methods ...

}