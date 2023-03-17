package com.jetpack.codechallenge.repository.remote

import com.jetpack.codechallenge.models.SchoolDetails
import com.jetpack.codechallenge.models.Schools
import com.jetpack.codechallenge.services.SchoolApi

interface SchoolRemoteDataSource {
    suspend fun fetchSchools(): Schools
    suspend fun fetchSchoolInfo(): SchoolDetails
}

class SchoolRemoteDataSourceImpl(private val schoolsApi: SchoolApi): SchoolRemoteDataSource {
    override suspend fun fetchSchools(): Schools {
        return schoolsApi.getSchools()
    }

    override suspend fun fetchSchoolInfo(): SchoolDetails {
        return schoolsApi.getSchoolInfo()
    }
}