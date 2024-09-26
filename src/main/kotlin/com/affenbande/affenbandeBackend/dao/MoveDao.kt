package com.affenbande.affenbandeBackend.dao

import com.affenbande.affenbandeBackend.model.Move
import org.springframework.stereotype.Component


@Component
class MoveDao: BaseDao<Move>(Move::class.java)