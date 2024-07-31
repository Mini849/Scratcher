package com.example.scratcher.ui.ui

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.scratcher.ui.services.ActivateCardWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ScratchViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val workManager: WorkManager,
) : ViewModel() {
    val workRequest = OneTimeWorkRequestBuilder<ActivateCardWorker>().build()

    var isLoading: MutableState<Boolean> = mutableStateOf(false)
        private set

    var uuid: MutableState<UUID?> = mutableStateOf(null)
        private set

    var isActivated: MutableState<Boolean> = mutableStateOf(false)
        private set

    var workResult: LiveData<WorkInfo>? = workManager.getWorkInfoByIdLiveData(workRequest.id)

    suspend fun generateUid() {
        isLoading.value = true
        delay(2000)
        uuid.value = UUID.randomUUID()
        isLoading.value = false
    }

    fun cancelTheOperation() {
        isLoading.value = false
        uuid.value = null //prevent possible race condition
    }

    fun activateCard() {
        workManager.enqueueUniqueWork(SCRATCH_ACTION, ExistingWorkPolicy.REPLACE, workRequest)
    }

    companion object {
        const val SCRATCH_ACTION = "SCRATCH_ACTION"
    }
}