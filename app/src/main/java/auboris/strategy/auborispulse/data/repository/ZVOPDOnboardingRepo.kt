package auboris.strategy.auborispulse.data.repository

import auboris.strategy.auborispulse.data.datastore.ZVOPDOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ZVOPDOnboardingRepo(
    private val zvopdOnboardingStoreManager: ZVOPDOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return zvopdOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            zvopdOnboardingStoreManager.setOnboardedState(state)
        }
    }
}