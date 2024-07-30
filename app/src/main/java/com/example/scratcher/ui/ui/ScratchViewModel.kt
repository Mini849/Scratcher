package com.example.scratcher.ui.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.scratcher.ui.repositories.server.ActivateServerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ScratchViewModel @Inject constructor(
    val activateServerRepository: ActivateServerRepository
) : ViewModel() {


    var isLoading: MutableState<Boolean> = mutableStateOf(false)
        private set

    var uuid: MutableState<UUID?> = mutableStateOf(null)
        private set

    var isActivated: MutableState<Boolean> = mutableStateOf(false)
        private set

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

    suspend fun activateCard() {

        isLoading.value = true
        try {
            val result = activateServerRepository.getVersion()
            if (result.android.toInt() > 277028) {
                isLoading.value = false
                isActivated.value = true
            } else {
                //todo error modal
                Log.e("ScratchViewModel", "error: ${result.android}")
            }
        } catch (e: Exception) {
            Log.e("ScratchViewModel", "activateCard: ${e.message}")
        }
    }

}