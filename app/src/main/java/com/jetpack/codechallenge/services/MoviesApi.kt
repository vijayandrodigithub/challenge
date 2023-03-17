package com.jetpack.codechallenge.services

import com.jetpack.codechallenge.models.SchoolDetails
import com.jetpack.codechallenge.models.Schools
import retrofit2.http.GET

interface SchoolApi {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(): Schools

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchoolInfo(): SchoolDetails
}