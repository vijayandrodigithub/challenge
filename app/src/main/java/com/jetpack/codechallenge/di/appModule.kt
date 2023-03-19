package com.jetpack.codechallenge.di


import com.jetpack.codechallenge.mappers.SchoolDataMapper
import com.jetpack.codechallenge.navigation.NavigationManager
import com.jetpack.codechallenge.services.SchoolApi
import com.jetpack.codechallenge.repository.remote.*
import com.jetpack.codechallenge.ui.home.SchoolViewModel
import com.jetpack.codechallenge.usecase.SchoolsUseCase
import com.jetpack.codechallenge.util.Constants
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single<SchoolApi> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(SchoolApi::class.java)
    }
}

val schoolModules = module {

    viewModel { SchoolViewModel(schoolsUseCase = get(), navigationManager = get()) }
    factory { SchoolsUseCase(repository = get()) }
    factory { NavigationManager() }
    factory { SchoolDataMapper() }
    factory<SchoolRemoteDataSource> { SchoolRemoteDataSourceImpl(schoolsApi = get()) }
    factory {
        SchoolsRepositoryDependencies(
            dataMapper = get(),
            remoteDataSource = get()
        )
    }

    factory<SchoolRepository> { SchoolsRepositoryImpl(dependencies = get()) }
}