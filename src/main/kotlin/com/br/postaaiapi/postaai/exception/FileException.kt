package com.br.postaaiapi.postaai.exception

private const val ERROR_TO_READ_FILE = "Error to read file"

private const val THE_IMAGE_FORMAT_MUST_BE_PNG_OR_JPG = "The image format must be PNG or JPG"

private const val FAIL_TO_CONVERT_URL_TO_URI = "fail to convert URL to URI"

private const val ERROR_TO_UPLOAD_FILE = "Error to upload file"

class FileException(message: String? = "File Error") : RuntimeException(message) {
    fun errorToReadFile(): FileException {
        return FileException(ERROR_TO_READ_FILE)
    }

    fun invalidImageFormat(): FileException {
        return FileException(THE_IMAGE_FORMAT_MUST_BE_PNG_OR_JPG)
    }

    fun errorToGetImageURI(): FileException {
        return FileException(FAIL_TO_CONVERT_URL_TO_URI)
    }

    fun errorUploadFile(): FileException {
        return FileException(ERROR_TO_UPLOAD_FILE)
    }

}