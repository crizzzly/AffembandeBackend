package com.affenbande.affenbandeBackend.dao.training

import com.affenbande.affenbandeBackend.dao.BaseDao
import com.affenbande.affenbandeBackend.model.training.TrainingGoal
import org.springframework.stereotype.Component

@Component
class TrainingGoalDao: BaseDao<TrainingGoal>(TrainingGoal::class.java)