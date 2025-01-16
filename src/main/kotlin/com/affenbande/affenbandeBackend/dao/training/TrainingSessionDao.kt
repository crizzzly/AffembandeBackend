package com.affenbande.affenbandeBackend.dao.training

import com.affenbande.affenbandeBackend.dao.BaseDao
import com.affenbande.affenbandeBackend.model.training.TrainingSession
import org.springframework.stereotype.Component

@Component
class TrainingSessionDao: BaseDao<TrainingSession>(TrainingSession::class.java)