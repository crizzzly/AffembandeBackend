package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.dao.ImagePathDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/files")
class FileUploadController {
    @Autowired
    lateinit var imagePathDao: ImagePathDao

    @PostMapping("/upload-image")
    fun uploadImage(
        @RequestParam("image_file") imageFile: MultipartFile,
        @RequestParam("model") model: String
    ): ResponseEntity<out Any> {
        val modelDirectory = when (model) {
            "sport" -> ImageConstants.SPORT_PATH
            "subcategory" -> ImageConstants.SUBCATEGORY_PATH
            "move" -> ImageConstants.MOVE_PATH
            else -> "/"
        }
        val filepath = modelDirectory + imageFile.originalFilename
        val imagePaths = handleImageInput(imageFile, filepath)
        imagePathDao.add(imagePaths)
        return ResponseEntity.ok(imagePaths)
    }

}