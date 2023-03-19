package com.jetpack.codechallenge.repository.remote

import android.util.Log
import com.jetpack.codechallenge.models.SchoolDetails
import com.jetpack.codechallenge.services.SchoolApi

interface SchoolRemoteDataSource {
    suspend fun fetchSchools(): List<SchoolDetails>
    suspend fun fetchSchoolInfo(dbn: String): List<SchoolDetails>
}

class SchoolRemoteDataSourceImpl(private val schoolsApi: SchoolApi): SchoolRemoteDataSource {
    override suspend fun fetchSchools(): List<SchoolDetails> {
        var data = schoolsApi.getSchools()
        Log.e(">>>>>>>","data: $data")
        return schoolsApi.getSchools()
    }

    override suspend fun fetchSchoolInfo(dbn: String): List<SchoolDetails> {
        return schoolsApi.getSchoolInfo(dbn)
    }
}