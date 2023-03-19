package com.jetpack.codechallenge.repository.remote

import com.jetpack.codechallenge.mappers.SchoolDataMapper
import com.jetpack.codechallenge.ui.home.models.SchoolItem

interface SchoolRepository {
    suspend fun fetchSchools(): List<SchoolItem>
    suspend fun fetchSchoolInfo(dbn: String): SchoolItem

}

class SchoolsRepositoryDependencies(
    val remoteDataSource: SchoolRemoteDataSource,
    val dataMapper: SchoolDataMapper
)

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

    /*override suspend fun fetchById(id: Int): MovieDetails {
        val remoteMovie = dependencies.remoteDataSource.fetchMovieById(id)
        val data = dependencies.dataMapper.map(remoteMovie)
        dependencies.localDataSource.insertMovies(listOf(data))
        return data
    }

    override suspend fun updateFavoriteMovie(id: Int, isBookmarked: Boolean): Int {
        return dependencies.localDataSource.updateFavoriteMovie(id, isBookmarked)
    }*/
}