package com.jetpack.codechallenge.usecase

import com.jetpack.codechallenge.repository.remote.SchoolRepository
import com.jetpack.codechallenge.ui.home.models.SchoolItem

class SchoolsUseCase(
    private val repository: SchoolRepository
) {

    suspend fun fetchSchools(): List<SchoolItem> {
        return repository.fetchSchools()
    }

    suspend fun fetchSchoolInfo(): SchoolItem {
        return repository.fetchSchoolInfo()
    }
}
