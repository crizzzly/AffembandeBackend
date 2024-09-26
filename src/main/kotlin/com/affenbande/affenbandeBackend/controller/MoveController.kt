package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.MoveDao
import com.affenbande.affenbandeBackend.model.Move
import com.affenbande.affenbandeBackend.model.Sport
import com.affenbande.affenbandeBackend.model.Subcategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class MoveController {
    @Autowired
    lateinit var moveDao: MoveDao

    @GetMapping("/moves/add")
    fun addMove(
        @RequestParam("name", required = true) name: String,
        @RequestParam("description", required = false) description: String,
        @RequestParam("imageSrc", required = false) imageSrc: String,
        @RequestParam("subcategoryIds", required = false) subcategoryIds: List<Int>,
        @RequestParam("sportIds", required = false) sportIds: List<Int>

    ): Move {
        val move = Move(
            name = name,
            description = description,
            imageSrc = imageSrc,
            subcategoryIds = subcategoryIds,
            sportIds = sportIds
        )
        moveDao.add(move)
        return move
    }

    @GetMapping("/moves/get-all")
    fun getAllMoves(): List<Move> {
        return moveDao.findAll()
    }

    @GetMapping("/moves/get-by-id")
    fun getMoveById(@RequestParam("id") id: kotlin.Int): Optional<Move> {
        val move = moveDao.findById(id)
        return move
    }

    @GetMapping("/moves/get-by-name")
    fun getMoveByName(@RequestParam("name") name: String): Move? {
        return moveDao.findByNameOrNull(name)
    }
}