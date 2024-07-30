package com.example.scratcher.ui.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.util.UUID
import javax.annotation.meta.When

@Composable
fun BoxScope.ScratchCard(uuid: UUID? = UUID.randomUUID(), isActivated: Boolean = false, isLoading: Boolean = false) {


    val currentStateOfCard = when {
        isLoading -> CardState.LOADING
        isActivated -> CardState.ACTIVATED
        uuid != null -> CardState.VISIBLE
        else -> CardState.HIDDEN
    }

    val animateContainerColor = when (currentStateOfCard) {
        CardState.LOADING -> MaterialTheme.colorScheme.primary
        CardState.HIDDEN -> MaterialTheme.colorScheme.primary
        CardState.VISIBLE -> MaterialTheme.colorScheme.secondary
        CardState.ACTIVATED -> MaterialTheme.colorScheme.secondary
    }

    val animateContentColor = when (currentStateOfCard) {
        CardState.LOADING -> MaterialTheme.colorScheme.onPrimary
        CardState.HIDDEN -> MaterialTheme.colorScheme.onPrimary
        CardState.VISIBLE -> MaterialTheme.colorScheme.onSecondary
        CardState.ACTIVATED -> MaterialTheme.colorScheme.onSecondary
    }

    Card(
        Modifier
            .align(Alignment.Center), elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp), colors = CardDefaults.cardColors(
            containerColor = animateContainerColor,
            contentColor = animateContentColor
        )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.size(300.dp)) {
            AnimatedContent(targetState = currentStateOfCard) {
                when (it) {
                    CardState.LOADING -> CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                    CardState.HIDDEN -> Icon(imageVector = Icons.Default.Favorite, contentDescription = "", Modifier.size(50.dp))
                    CardState.VISIBLE -> Text(text = uuid.toString(), Modifier.padding(16.dp), textAlign = TextAlign.Center)
                    CardState.ACTIVATED -> Text(text = "Activated")
                }
            }
        }
    }
}

enum class CardState {
    LOADING,
    HIDDEN,
    VISIBLE,
    ACTIVATED
}