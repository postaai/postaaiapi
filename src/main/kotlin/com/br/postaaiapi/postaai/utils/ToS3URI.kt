package com.br.postaaiapi.postaai.utils

fun String.toS3URI(): String {
    val regex = Regex("""https://(.+?)\.s3\..+?\.amazonaws\.com/(.+)""")
    val url = this
    return regex.replace(url) { matchResult ->
        val bucket = matchResult.groupValues[1]
        val path = matchResult.groupValues[2]
        "s3://$bucket/$path"
    }
}