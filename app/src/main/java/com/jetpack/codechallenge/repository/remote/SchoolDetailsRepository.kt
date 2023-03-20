package com.jetpack.codechallenge.repository.remote

import com.jetpack.codechallenge.mappers.SchoolDataMapper
import com.jetpack.codechallenge.ui.home.models.SchoolItem

interface SchoolRepository {
    suspend fun fetchSchools(): List<SchoolItem>
    suspend fun fetchSchoolInfo(dbn: String): SchoolItem

}

/**
 * SchoolsRepositoryDependencies can give multiple data source inputs like Remote or Local
 */
class SchoolsRepositoryDependencies(
    val remoteDataSource: SchoolRemoteDataSource,
    val dataMapper: SchoolDataMapper
)

/**
 * SchoolsRepositoryImpl It helps the fetching data from sources
 */
class SchoolsRepositoryImpl(private val dependencies: SchoolsRepositoryDependencies) :
    SchoolRepository {
    override suspend fun fetchSchools(): List<SchoolItem> {
        val schools = dependencies.remoteDataSource.fetchSchools()
        return schools.map { dependencies.dataMapper.mapSchoolItem(it) }
    }

    override suspend fun fetchSchoolInfo(dbn: String): SchoolItem {
        val schools = dependencies.remoteDataSource.fetchSchoolInfo(dbn)
        return dependencies.dataMapper.mapSchoolItem(schools[0])
    }
}