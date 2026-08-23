package auboris.strategy.auborispulse.di

import auboris.strategy.auborispulse.data.datastore.ZVOPDOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { ZVOPDOnboardingPrefs(androidContext()) }
}