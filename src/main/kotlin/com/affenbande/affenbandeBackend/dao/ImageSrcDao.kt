package com.affenbande.affenbandeBackend.dao

import com.affenbande.affenbandeBackend.model.ImageSrc
import org.springframework.stereotype.Component

@Component
class ImageSrcDao: BaseDao<ImageSrc>(ImageSrc::class.java)