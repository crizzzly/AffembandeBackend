package com.affenbande.affenbandeBackend.dao.training

import com.affenbande.affenbandeBackend.dao.BaseDao
import com.affenbande.affenbandeBackend.model.training.TrainingSet
import org.springframework.stereotype.Component

@Component
class TrainingSetDao: BaseDao<TrainingSet>(TrainingSet::class.java)