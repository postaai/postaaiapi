package com.br.postaaiapi.postaai.gateway

import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.net.URI

interface S3Gateway {

    fun uploadImage(bucket: String?, image: MultipartFile, fileName: String, folderName: String): URI

    fun uploadImage(bucket: String?, image: BufferedImage, fileName: String, folderName: String): URI

}