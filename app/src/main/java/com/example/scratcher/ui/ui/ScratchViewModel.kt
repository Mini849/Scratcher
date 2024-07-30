package com.example.scratcher.ui.ui

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ScratchViewModel @Inject constructor() : ViewModel() {


    var isLoading: MutableState<Boolean> = mutableStateOf(false)
        private set

    var uuid: MutableState<UUID?> = mutableStateOf(null)
        private set

    var isCardCodeVisible: MutableState<Boolean> = mutableStateOf(uuid.value != null)

    var iActivated: MutableState<Boolean> = mutableStateOf(false)
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

}