package auboris.strategy.auborispulse.di

import auboris.strategy.auborispulse.ui.viewmodel.BookingViewModel
import auboris.strategy.auborispulse.ui.viewmodel.CheckoutViewModel
import auboris.strategy.auborispulse.ui.viewmodel.ZVOPDOnboardingVM
import auboris.strategy.auborispulse.ui.viewmodel.ServiceDetailsViewModel
import auboris.strategy.auborispulse.ui.viewmodel.ServiceViewModel
import auboris.strategy.auborispulse.ui.viewmodel.ZVOPDSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        ZVOPDSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ZVOPDOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}