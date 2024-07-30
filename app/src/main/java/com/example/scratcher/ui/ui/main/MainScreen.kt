package com.example.scratcher.ui.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.scratcher.ui.ui.ScratchCard
import com.example.scratcher.ui.ui.ScratchViewModel

@Composable
fun MainScreen(navController: NavHostController, scratchViewModel: ScratchViewModel) {

    val isActivated = scratchViewModel.iActivated
    val isCardCodeVisible = scratchViewModel.isCardCodeVisible

    Box(Modifier.fillMaxSize()) {
        ScratchCard(uuid = scratchViewModel.uuid.value, isActivated.value)
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(8.dp),
        ) {

            Button(onClick = {
                navController.navigate(
                    Routes.ScratchScreen
                )
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Scratch it!")
            }

            Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = {
                    navController.navigate(Routes.ActivationScreen)
                }, modifier = Modifier.weight(1f), enabled = isCardCodeVisible.value,
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