package com.affenbande.affenbandeBackend.services

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.dao.SportDao
import com.affenbande.affenbandeBackend.dao.SubcategoryDao
import com.affenbande.affenbandeBackend.dto.MoveRequestDTO
import com.affenbande.affenbandeBackend.dto.MoveResponseDTO
import com.affenbande.affenbandeBackend.model.ImagePath
import com.affenbande.affenbandeBackend.model.Move
import com.affenbande.affenbandeBackend.model.Sport
import com.affenbande.affenbandeBackend.model.Subcategory
import com.affenbande.affenbandeBackend.services.MoveService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.any
import java.util.*

class MoveServiceTest {

    @InjectMockKs
    private lateinit var moveService: MoveService

    @MockK
    private lateinit var moveDao: MoveDao

    @MockK
    private lateinit var subcategoryDao: SubcategoryDao

    @MockK
    private lateinit var sportDao: SportDao

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }


    @Test
    fun `addMove should add a new move`() {
        // Given
        val moveRequestDTO = MoveRequestDTO(
            name = "Test Move",
            sports = listOf("1","2"),
            subcategories = listOf("1","2"),
            isCoreMove = false,
            level = 0,
            intensity = 0,
            frequency = 0,
            timePreparation = 0,
            timeExercise = 0,
            formula = null,
            preMoves = listOf("1","2"),
            optPreMoves = listOf("1","2"),
            description = null,
        )
        val mockMove = mockk<Move>()
        every { mockMove.toResponseDTO() } returns mockk()
        every { moveDao.add(any()) } returns Unit
        every { subcategoryDao.findByIdOrNull(any()) } returns Subcategory()
        every { sportDao.findByIdOrNull(any()) } returns Sport()
        every { moveDao.findByIdOrNull(any()) } returns mockMove


        // When
        moveService.addMove(moveRequestDTO)

        // Then
        verify { moveDao.add(any()) }  // Verify add() is called
        verify(exactly = 1) { sportDao.findByIdOrNull(1) }  // Verify findByIdOrNull() for each ID individually
        verify(exactly = 1) { sportDao.findByIdOrNull(2) }
        verify(exactly = 1) { subcategoryDao.findByIdOrNull(1) }
        verify(exactly = 1) { subcategoryDao.findByIdOrNull(2) }

        verify(exactly = 2) { moveDao.findByIdOrNull(1) } // Verify distinct calls for each preMove
        verify(exactly = 2) { moveDao.findByIdOrNull(2) }
        verify(exactly = 2) { moveDao.findByIdOrNull(1) } // and distinct calls for each optPreMove
        verify(exactly = 2) { moveDao.findByIdOrNull(2) }
    }

    @Test
    fun `getAllMoves should return all moves`() {
        // Given
        val expectedMoves = listOf(mockk<Move>(), mockk<Move>())
        every { moveDao.findAll() } returns expectedMoves
        every { expectedMoves[0].toResponseDTO() } returns mockk()
        every { expectedMoves[1].toResponseDTO() } returns mockk()


        // When
        val result = moveService.getAllMoves()

        // Then
        assertEquals(2, result.size)  // Asserting the size of the returned list
        verify(exactly = 1) { moveDao.findAll() }
        verify { expectedMoves[0].toResponseDTO() }
        verify { expectedMoves[1].toResponseDTO() }

    }


    @Test
    fun `getMoveById should return the move with the given ID`() {
        // Given (Setting up the test)
        val moveId = 1
        val expectedMove = mockk<Move>()  // Create a mock Move object
        every { expectedMove.toResponseDTO() } returns mockk()

        every { moveDao.findByIdOrNull(moveId) } returns expectedMove  // Mock the DAO call

        // When (Executing the code under test)
        val result = moveService.getMoveById(moveId)  // Call the service method

        // Then  (Verifying the outcome)
        assertEquals(expectedMove.toResponseDTO(), result) // or assert specific properties of the result
        verify(exactly = 1) { moveDao.findByIdOrNull(moveId) }
        verify { expectedMove.toResponseDTO() }
    }


    @Test
    fun `getMoveByName should return the move with the given name`() {
        // Given
        val moveName = "Flips"
        val expectedMove = mockk<Move>()
        every { moveDao.findByNameOrNull(moveName) } returns expectedMove
        every { expectedMove.toResponseDTO() } returns mockk()


        // When
        val result = moveService.getMoveByName(moveName)
        // Then
        assertEquals(expectedMove.toResponseDTO(), result)
        verify(exactly = 1) { moveDao.findByNameOrNull(moveName) }
        verify { expectedMove.toResponseDTO() }
    }


    @Test
    fun `updateMove should update the move with the given ID`() {
        // Given
        val moveId = 0
        val updatedName = "Test Move"

        val moveRequestDTO = MoveRequestDTO(
            name = updatedName,
            sports = listOf("1","2"),
            subcategories = listOf("1","2"),
            isCoreMove = false,
            level = 0,
            intensity = 0,
            frequency = 0,
            timePreparation = 0,
            timeExercise = 0,
            formula = null,
            preMoves = listOf("1","2"),
            optPreMoves = listOf("1","2"),
            description = null,
        )
        val existingMove = mockk<Move>()
        val updatedMoveDTO = mockk<MoveResponseDTO>()


        every { moveDao.findByIdOrNull(moveId) } returns existingMove
        every { existingMove.id } returns moveId // Mock getter value which update depends on
        every { existingMove.name } returns "Old Name" // Mock getter value

        // Return existingMove's DTO after mocking toResponseDTO method to return updatedMoveDTO
        every { existingMove.toResponseDTO() } returns updatedMoveDTO
        every { moveDao.update(match { it.name == updatedName }) } returns Move() // Update is void in the real service method        // When

        val result = moveService.updateMove(moveId, moveRequestDTO)

        // Then
        verify { moveDao.findByIdOrNull(moveId) }
        verify { moveDao.update(match { it.name == updatedName }) }
        assertEquals(updatedMoveDTO, result)  // Correct assertion (on DTO)

    }

    @Test
    fun `deleteMove should delete the move with the given ID`() {
        // Given
        val moveId = 1
        every { moveDao.deleteById(any()) } returns Unit

        // When
        moveService.deleteMove(moveId)

        // Then
        verify(exactly = 1) { moveDao.deleteById(moveId) }
    }


}