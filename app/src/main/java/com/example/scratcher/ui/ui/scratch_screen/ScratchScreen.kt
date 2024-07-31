package com.example.scratcher.ui.ui.scratch_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.scratcher.R
import com.example.scratcher.ui.ui.ScratchCard
import com.example.scratcher.ui.ui.ScratchViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Composable
fun ScratchScreen(navController: NavHostController, scratchViewModel: ScratchViewModel) {

    val scopes = rememberCoroutineScope()

    BackHandler {
        if (scratchViewModel.isLoading.value) {
            if (scopes.isActive) {
                scopes.cancel()
            }
            scratchViewModel.cancelTheOperation()
        }
        navController.popBackStack()
    }

    Box(Modifier.fillMaxSize()) {
        ScratchCard(uuid = scratchViewModel.uuid.value, isActivated = scratchViewModel.isActivated.value, isLoading = scratchViewModel.isLoading.value)
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Button(onClick = {
             scopes.launch {
                 scratchViewModel.generateUid()
             }
            }, modifier = Modifier.weight(1f)) {
                Text(text = stringResource(id = R.string.scratch_it))
            }
        }
    }
}