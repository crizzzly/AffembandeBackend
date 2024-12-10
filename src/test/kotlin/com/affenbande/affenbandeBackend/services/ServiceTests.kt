package com.affenbande.affenbandeBackend.services

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.SportRequestDTO
import com.affenbande.affenbandeBackend.dto.SubcategoryRequestDTO
import com.affenbande.affenbandeBackend.model.ImagePath
import com.affenbande.affenbandeBackend.model.Move
import com.affenbande.affenbandeBackend.model.Sport
import com.affenbande.affenbandeBackend.model.Subcategory
import com.affenbande.affenbandeBackend.services.SportService
import com.affenbande.affenbandeBackend.services.SubcategoryService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

class SportServiceTest {

    @InjectMockKs
    private lateinit var sportService: SportService

    @MockK
    private lateinit var sportDao: SportDao

    @MockK
    private lateinit var moveDao: MoveDao

    @MockK
    private lateinit var imagePathDao: ImagePathDao

    @MockK
    private lateinit var subcategoryDao: SubcategoryDao


    @Test
    fun `addSport should add a new sport`() {
        // Given
        val sportRequestDTO = SportRequestDTO(
            name = "Test Sport",
            subcategories = listOf("Subcategory 1", "Subcategory 2"),
            moves = listOf("Move 1", "Move 2"),
            imagePathId = 2
        )
        val expectedImagePath = ImagePath(2, "path")
        val mockSport = mockk<Sport>()

        every { mockSport.toResponseDTO() } returns mockk()
        every { imagePathDao.findById(any()) } returns Optional.of(expectedImagePath)
        every { subcategoryDao.findByNameOrNull(any()) } returns Subcategory()
        every { moveDao.findByNameOrNull(any()) } returns Move()
        every { sportDao.add(any()) } returns mockSport as Unit



        // When
        val result = sportService.addSport(sportRequestDTO)

        // Then
        verify { sportDao.add(any()) }
        verify { imagePathDao.findById(any()) }
        verify(exactly = 2) { subcategoryDao.findByNameOrNull(any()) }
        verify(exactly = 2) { moveDao.findByNameOrNull(any()) }
        confirmVerified(sportDao, imagePathDao, subcategoryDao, moveDao)

    }


}


