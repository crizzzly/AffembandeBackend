package com.affenbande.affenbandeBackend.controller

import com.affenbande.affenbandeBackend.model.ImagePath
import org.springframework.web.multipart.MultipartFile
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Paths
import javax.imageio.ImageIO

fun isPathWritable(path: String): Boolean {
    val filePath = Paths.get(path)
    return Files.isWritable(filePath)
}


fun handleImageInput(imageFile: MultipartFile, filePath: String): ImagePath {

//    println("Received file: $filePath")

    // Generate resized images for different sizes
    val xsImage = resizeImage(imageFile, 100)  // Example size
    val sImage = resizeImage(imageFile, 300)   // Example size
    val mImage = resizeImage(imageFile, 500)   // Example size
    val lImage = resizeImage(imageFile, 800)   // Example size
    val xlImage = resizeImage(imageFile, 1200) // Example size

    val splittedPath = filePath.split(".")
    val pathWithoutExtension = splittedPath.first()
    var extension = splittedPath.last()
    
    if (extension != "gif" && extension != "GIF") {
        extension = "jpeg"
    }

    val imagePath = ImagePath()
    imagePath.name = filePath
    imagePath.xs = saveImage(xsImage, "$pathWithoutExtension-xs.${extension}")
    imagePath.s = saveImage(sImage, "$pathWithoutExtension-s.${extension}")
    imagePath.m = saveImage(mImage, "$pathWithoutExtension-m.${extension}")
    imagePath.l = saveImage(lImage, "$pathWithoutExtension-l.${extension}")
    imagePath.xl = saveImage(xlImage, "$pathWithoutExtension-xl.${extension}")

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
    if (image!!.width <= 0 || image.height <= 0) {
        println("Invalid image dimensions: ${image.width}x${image.height}")
        throw Exception("Invalid image to save")
    } else
    {
        val outputFile = File(filePath)
        try {
//            println("Attempting to save image to: ${outputFile.absolutePath}")
            if (!outputFile.parentFile.exists()) {
                println("Parent directory does not exist, creating directories: ${outputFile.parentFile.absolutePath}")
                outputFile.parentFile.mkdirs() // Ensure directories exist
            }

            if(outputFile.exists()) {
                println("File already exists: ${outputFile.absolutePath}")
                return outputFile.absolutePath
            }

            if (! isPathWritable(outputFile.parentFile.absolutePath)) {
                println("Parent directory is not writable: ${outputFile.parentFile.absolutePath}")
                throw Exception("Parent directory is not writable: ${outputFile.parentFile.absolutePath}")
            }

            // Determine the image format based on the file extension
            val format = when {
                outputFile.absolutePath.endsWith(".gif", ignoreCase = true) -> "gif"
//                outputFile.absolutePath.endsWith(".GIF", ignoreCase = true) -> "GIF"
                else -> "jpeg" // Default to JPG
            }


            val writers = ImageIO.getImageWritersByFormatName(format)
            if (!writers.hasNext()) {
                println("No writers available for format: $format")
                throw Exception("No writers available for format: $format")
            } else {
                while (writers.hasNext()) {
                    println("Available writer: ${writers.next().originatingProvider}")
                }
            }

//            println("Image width: ${image.width}, height: ${image.height}")

            // Create an OutputStream to save the image
            FileOutputStream(outputFile).use { outputStream: OutputStream ->
                val result = ImageIO.write(image, format, outputStream)
                if (!result) {
                    println("ImageIO.write returned false, failed to write the image")
                } else {
                    println("Image saved successfully: ${outputFile.absolutePath}")
                }
            }
            return outputFile.absolutePath
        } catch (e: Exception) {
            println("Failed to save image: ${e.message}")
            e.printStackTrace()
        }
        throw Exception("Failed to save image")
    }
}