package com.br.postaaiapi.postaai.gateway.impl

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.br.postaaiapi.postaai.exception.FileException
import com.br.postaaiapi.postaai.gateway.S3Gateway
import org.apache.commons.io.FilenameUtils
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URI
import javax.imageio.ImageIO

private const val PNG = "png"

private const val JPG = "jpg"


private const val CONTENT_TYPE = "image"

@Service
class S3GatewayImpl(
    private val s3Client: AmazonS3
) : S3Gateway {

    override fun uploadFile(bucket: String?, image: MultipartFile, fileName: String, folderName: String): URI {

        checkExtension(image)
        val meta = ObjectMetadata()
        meta.contentType = CONTENT_TYPE
        val inputStream = getImageS3(image)
        s3Client.putObject(bucket, "$folderName/$fileName", inputStream, meta)

        try {
            return s3Client.getUrl(bucket, "$folderName/$fileName").toURI()
        } catch (e: Exception) {
            throw FileException().errorToGetImageURI()
        }
    }

    private fun checkExtension(image: MultipartFile) {
        if (PNG != getExtension(image) && JPG != getExtension(image)) {
            throw FileException().invalidImageFormat()
        }
    }

    private fun getImageS3(image: MultipartFile): InputStream {
        try {
            val img = ImageIO.read(image.inputStream)
            return getInputStream(img)
        } catch (e: IOException) {
            throw FileException().errorToReadFile()
        }
    }

    private fun getExtension(image: MultipartFile): String {
        return FilenameUtils.getExtension(image.originalFilename)
    }

    private fun getInputStream(image: BufferedImage): InputStream {
        try {
            val os = ByteArrayOutputStream()
            ImageIO.write(image, PNG, os)
            return ByteArrayInputStream(os.toByteArray())
        } catch (e: IOException) {
            throw FileException().errorToReadFile()
        }
    }
}