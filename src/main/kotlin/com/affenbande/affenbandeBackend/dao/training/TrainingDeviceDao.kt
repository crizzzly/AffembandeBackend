package com.affenbande.affenbandeBackend.dao.training

import com.affenbande.affenbandeBackend.dao.BaseDao
import com.affenbande.affenbandeBackend.model.training.TrainingDevice
import org.springframework.stereotype.Component

@Component
class TrainingDeviceDao: BaseDao<TrainingDevice>(TrainingDevice::class.java)