package com.jetpack.codechallenge.services

import com.jetpack.codechallenge.models.SchoolDetails
import retrofit2.http.GET
import retrofit2.http.Query


interface SchoolApi {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(): List<SchoolDetails>
    @GET("resource/f9bf-2cp4.json")
    suspend fun getSchoolInfo(@Query("dbn") dbn: String): List<SchoolDetails>
}