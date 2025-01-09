package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import com.affenbande.affenbandeBackend.model.ImagePath
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
@RestController
class ImagePathController {
    @Autowired
    lateinit var imagePathDao: ImagePathDao

    @PostMapping("/images/add", produces = ["application/json,charset=utf8"])
    fun addImageSrc(imagePath: ImagePath) {
        return imagePathDao.add(imagePath)
    }


    @GetMapping("/images/get-all", produces = ["application/json,charset=utf8"])
    fun getImageSrc(): List<ImagePath> {
        return imagePathDao.findAll()
    }

}