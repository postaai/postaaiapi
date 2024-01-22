package com.br.postaaiapi.postaai.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config {

    @Value("\${aws.access_key_id}")
    private val awsId: String? = null

    @Value("\${aws.secret_access_key}")
    private val awsKey: String? = null

    @Value("\${s3.region}")
    private val region: String? = null


    @Bean
    fun s3Client(): AmazonS3 {
        val awsCredential = BasicAWSCredentials(awsId, awsKey)
        return AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(awsCredential))
            .build()
    }
}