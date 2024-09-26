package com.affenbande.affenbandeBackend.dao

import com.affenbande.affenbandeBackend.model.Sport
import org.springframework.stereotype.Component

@Component
class SportDao: BaseDao<Sport>(Sport::class.java)
