package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.ImageSrcDao
import com.affenbande.affenbandeBackend.model.ImageSrc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ImageSrcController {
    @Autowired
    lateinit var imageSrcDao: ImageSrcDao

    @GetMapping("/imagesrc/add")
    fun addImageSrc(imageSrc: ImageSrc) {
        return imageSrcDao.add(imageSrc)
    }


    @GetMapping("/imagesrc/get-all")
    fun getImageSrc(): List<ImageSrc> {
        return imageSrcDao.findAll()
    }

}