package com.affenbande.affenbandeBackend.dao

import com.affenbande.affenbandeBackend.model.ImagePath
import org.springframework.stereotype.Component

@Component
class ImagePathDao: BaseDao<ImagePath>(ImagePath::class.java)