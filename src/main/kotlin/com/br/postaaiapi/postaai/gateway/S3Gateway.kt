package com.br.postaaiapi.postaai.gateway

import org.springframework.web.multipart.MultipartFile
import java.net.URI

interface S3Gateway {

    fun uploadFile(bucket: String?, image: MultipartFile, fileName: String, folderName: String): URI
}