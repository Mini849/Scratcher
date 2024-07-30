package com.example.scratcher.ui.ui.activation_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.work.WorkInfo
import com.example.scratcher.ui.ui.ScratchCard
import com.example.scratcher.ui.ui.ScratchViewModel
import kotlinx.coroutines.launch

@Composable
fun ActivationScreen(navController: NavHostController, scratchViewModel: ScratchViewModel) {

    val scopes = rememberCoroutineScope()

    val result = scratchViewModel.workResult.observeAsState()

    when (result.value?.state) {
        WorkInfo.State.SUCCEEDED -> {
            scratchViewModel.isLoading.value = false
            scratchViewModel.isActivated.value = true
        }

        WorkInfo.State.FAILED -> {
            //todo modal window
            scratchViewModel.isLoading.value = false
        }

        WorkInfo.State.RUNNING -> {
            scratchViewModel.isLoading.value = true
        }

        else -> {
            scratchViewModel.isLoading.value = false
        }
    }

    Box(Modifier.fillMaxSize()) {
        ScratchCard(uuid = scratchViewModel.uuid.value, scratchViewModel.isActivated.value, isLoading = scratchViewModel.isLoading.value)
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(8.dp),
        ) {

            Button(
                onClick = {
                    scopes.launch {
                        scratchViewModel.activateCard()
                    }
                }, modifier = Modifier.weight(1f), enabled = !scratchViewModel.isActivated.value,
                colors = ButtonDefaults.buttonColors(
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.LightGray
                )
            ) {
                Text(text = "Activate!")
            }
        }
    }
}