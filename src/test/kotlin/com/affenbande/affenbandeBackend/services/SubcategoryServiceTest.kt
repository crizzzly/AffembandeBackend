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
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*


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

    @Test
    fun `addSubcategory should add a new subcategory`() {
        val subcategoryRequestDTO = SubcategoryRequestDTO(
            name = "Test Subcategory",
            imagePathId = 2,
            sports = listOf("Sport 1", "Sport 2"),
            moves = listOf("Move 1", "Move 2")
        )
        val expectedImagePath = ImagePath(2, "testpath")
        val mockSubcategory = mockk<Subcategory>()
        every { mockSubcategory.toResponseDTO() } returns mockk()

        every { imagePathDao.findById(any()) } returns Optional.of(expectedImagePath)
        every { sportDao.findByNameOrNull(any()) } returns Sport()
        every { moveDao.findByNameOrNull(any()) } returns Move()
        every { subcategoryDao.add(any()) } returns mockSubcategory as Unit


        // When
        val result = subcategoryService.addSubcategory(subcategoryRequestDTO)
        // Then
        verify { imagePathDao.findById(any()) }
        verify(exactly = 2) { sportDao.findByNameOrNull(any()) }
        verify(exactly = 2) { moveDao.findByNameOrNull(any()) }
        verify { subcategoryDao.add(any()) }


    }


}