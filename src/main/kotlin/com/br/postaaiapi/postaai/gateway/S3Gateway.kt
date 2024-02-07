package com.br.postaaiapi.postaai.gateway

import org.springframework.web.multipart.MultipartFile
import java.net.URI

interface S3Gateway {

    fun uploadImage(bucket: String?, image: MultipartFile, fileName: String, folderName: String): URI

    fun uploadObject(bucket: String?, file: MultipartFile, fileName: String, folderName: String): URI

}