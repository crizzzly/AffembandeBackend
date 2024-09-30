package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.ImageSrcDao
import com.affenbande.affenbandeBackend.model.ImagePath
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ImagePathController {
    @Autowired
    lateinit var imageSrcDao: ImageSrcDao

    @GetMapping("/imagesrc/add")
    fun addImageSrc(imagePath: ImagePath) {
        return imageSrcDao.add(imagePath)
    }


    @GetMapping("/imagesrc/get-all")
    fun getImageSrc(): List<ImagePath> {
        return imageSrcDao.findAll()
    }

}