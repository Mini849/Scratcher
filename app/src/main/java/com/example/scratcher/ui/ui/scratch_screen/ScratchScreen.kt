package com.example.scratcher.ui.ui.scratch_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.scratcher.ui.ui.main.Routes

@Composable
fun ScratchScreen(navController: NavHostController) {
    val cardState by remember {
        mutableStateOf(false)
    }

    Box(Modifier.fillMaxSize()) {
        Card(
            Modifier
                .align(Alignment.Center), elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp), colors = CardDefaults.cardColors(
                containerColor = if (cardState) Color.White else MaterialTheme.colorScheme.primary,
                contentColor = if (cardState) Color.Black else MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.size(300.dp)) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "", Modifier.size(50.dp))
            }
        }
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

        }
    }
}