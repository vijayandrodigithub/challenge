package com.jetpack.codechallenge

import android.app.Application
import com.jetpack.codechallenge.di.appModule
import com.jetpack.codechallenge.di.schoolModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class ChallengeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        koinConfiguration()
    }

    private fun koinConfiguration() {
        GlobalContext.startKoin {
            androidContext(this@ChallengeApplication)
            modules(appModule, schoolModules)
        }
    }
}