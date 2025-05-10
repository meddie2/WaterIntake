package com.example.waterintake.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waterintake.R
import com.example.waterintake.model.AppResources
import com.example.waterintake.viewmodel.WelcomeViewModel

@Composable
fun WelcomeScreen(
    onNavigateToHome: () -> Unit,
    viewModel: WelcomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scale by animateFloatAsState(
        targetValue = if (uiState.isVisible) 1f else 0.8f,
        animationSpec = tween(durationMillis = 500),
        label = "scale"
    )

    LaunchedEffect(Unit) {
        viewModel.updateVisibility(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.screen_padding)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_large))
        ) {
            AnimatedVisibility(
                visible = uiState.isVisible,
                enter = fadeIn(animationSpec = tween(1000)) + slideInVertically(
                    initialOffsetY = { -it },
                    animationSpec = tween(1000)
                )
            ) {
                Text(
                    text = AppResources.Strings.APP_NAME,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            AnimatedVisibility(
                visible = uiState.isVisible,
                enter = fadeIn(animationSpec = tween(1000, delayMillis = 500))
            ) {
                Text(
                    text = AppResources.Strings.WELCOME_MESSAGE,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }

            AnimatedVisibility(
                visible = uiState.isVisible,
                enter = fadeIn(animationSpec = tween(1000, delayMillis = 1000))
            ) {
                Text(
                    text = AppResources.Strings.WATER_IMPORTANCE_1,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            AnimatedVisibility(
                visible = uiState.isVisible,
                enter = fadeIn(animationSpec = tween(1000, delayMillis = 1500))
            ) {
                Button(
                    onClick = onNavigateToHome,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.button_height))
                        .scale(scale),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = AppResources.Strings.GET_STARTED,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
} 