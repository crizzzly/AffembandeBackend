package com.affenbande.affenbandeBackend.dao.training

import com.affenbande.affenbandeBackend.dao.BaseDao
import com.affenbande.affenbandeBackend.model.training.TrainingPlan
import org.springframework.stereotype.Component

@Component
class TrainingPlanDao: BaseDao<TrainingPlan>(TrainingPlan::class.java)