package com.example.scratcher.ui.ui.activation_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.work.WorkInfo
import com.example.scratcher.R
import com.example.scratcher.ui.ui.ScratchCard
import com.example.scratcher.ui.ui.ScratchViewModel
import kotlinx.coroutines.launch

@Composable
fun ActivationScreen(scratchViewModel: ScratchViewModel) {

    val scopes = rememberCoroutineScope()

    val showErrorDialog = remember { mutableStateOf(false) }

    LaunchedEffect(scratchViewModel.workResult) {
        scratchViewModel.workResult?.observeForever { workInfo ->
            when (workInfo?.state) {
                WorkInfo.State.SUCCEEDED -> {
                    scratchViewModel.isLoading.value = false
                    scratchViewModel.isActivated.value = true
                }
                WorkInfo.State.FAILED -> {
                    showErrorDialog.value = true
                    scratchViewModel.isLoading.value = false
                }
                WorkInfo.State.RUNNING -> {
                    scratchViewModel.isLoading.value = true
                }
                else -> {
                    scratchViewModel.isLoading.value = false
                }
            }
        }
    }

    Box(Modifier.fillMaxSize()) {
        ScratchCard(uuid = scratchViewModel.uuid.value, scratchViewModel.isActivated.value, isLoading = scratchViewModel.isLoading.value)

        if (showErrorDialog.value) {
            ErrorDialog(error = stringResource(R.string.error_has_occurred), onDismissDialog = {
                showErrorDialog.value = false
            })
        }

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
                Text(text = stringResource(R.string.activate))
            }
        }
    }
}

@Composable
fun ErrorDialog(error: String, onDismissDialog: () -> Unit) {
    Dialog(onDismissRequest = { onDismissDialog() }) {
        Surface(shape = RoundedCornerShape(25.dp), shadowElevation = 8.dp) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {
                Row {
                    Text(text = stringResource(R.string.error), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
                Row {
                    Text(text = error, Modifier.padding(vertical = 16.dp))
                }
                Row {
                    Button(onClick = { onDismissDialog() }, Modifier.fillMaxWidth()) {
                        Text(text = stringResource(R.string.ok))
                    }
                }
            }
        }
    }
}

