package com.jetpack.codechallenge.usecase

import com.jetpack.codechallenge.repository.remote.SchoolRepository
import com.jetpack.codechallenge.ui.home.models.SchoolItem

/**
 * SchoolsUseCase help to domain layer between viewmodel and Datalayer
 */
class SchoolsUseCase(
    private val repository: SchoolRepository
) {

    suspend fun fetchSchools(): List<SchoolItem> {
        return repository.fetchSchools()
    }

    suspend fun fetchSchoolInfo(dbn: String): SchoolItem {
        return repository.fetchSchoolInfo(dbn)
    }
}
