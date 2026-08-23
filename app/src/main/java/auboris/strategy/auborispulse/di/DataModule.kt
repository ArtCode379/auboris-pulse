package auboris.strategy.auborispulse.di

import auboris.strategy.auborispulse.data.repository.BookingRepository
import auboris.strategy.auborispulse.data.repository.ZVOPDOnboardingRepo
import auboris.strategy.auborispulse.data.repository.ServiceRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        ZVOPDOnboardingRepo(
            zvopdOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ServiceRepository() }

    single{
        BookingRepository(
            bookingDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}