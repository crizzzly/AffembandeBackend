package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.model.ImagePath
import org.springframework.web.multipart.MultipartFile
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.File
import javax.imageio.ImageIO


fun handleImageInput(imageFile: MultipartFile, filePath: String): ImagePath {

    println("Received file: $filePath")

    // Generate resized images for different sizes
    val xsImage = resizeImage(imageFile, 100)  // Example size
    val sImage = resizeImage(imageFile, 300)   // Example size
    val mImage = resizeImage(imageFile, 500)   // Example size
    val lImage = resizeImage(imageFile, 800)   // Example size
    val xlImage = resizeImage(imageFile, 1200) // Example size

    val imagePath = ImagePath()
    imagePath.name = filePath
    imagePath.xs = saveImage(xsImage, "$filePath-xs.webp")
    imagePath.s = saveImage(sImage, "$filePath-s.webp")
    imagePath.m = saveImage(mImage, "$filePath-m.webp")
    imagePath.l = saveImage(lImage, "$filePath-l.webp")
    imagePath.xl = saveImage(xlImage, "$filePath-xl.webp")

    return imagePath
}

fun resizeImage(imageFile: MultipartFile, width: Int): BufferedImage? {
    return try {
        val inputStream = ByteArrayInputStream(imageFile.bytes)
        val originalImage: BufferedImage = ImageIO.read(inputStream)

        // Calculate new dimensions while keeping the aspect ratio
        val aspectRatio = originalImage.width.toDouble() / originalImage.height
        val height = (width / aspectRatio).toInt()

        val scaledImage: Image = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)
        val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val graphics = bufferedImage.createGraphics()
        graphics.drawImage(scaledImage, 0, 0, null)
        graphics.dispose()

        bufferedImage
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun saveImage(image: BufferedImage?, filePath: String): String {
    if (image != null) {
        val outputFile = File(filePath)
        ImageIO.write(image, "webp", outputFile)
        return outputFile.absolutePath
    }
    return "Failed to save image"
}