package com.example.waterintake.ui.screens.welcome

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.waterintake.R
import com.example.waterintake.ui.components.PrimaryButton

@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        visible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.screen_padding))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_lg))
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_xl)))
        
        // App Logo
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(R.dimen.image_size_lg))
            )
        }
        
        // Welcome Text
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_sm))
            ) {
                Text(
                    text = stringResource(R.string.welcome_message),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.welcome_subtitle),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        // Features
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_md))
            ) {
                FeatureItem(
                    imageResId = R.drawable.feature_track,
                    title = stringResource(R.string.feature_track_title),
                    description = stringResource(R.string.feature_track_desc)
                )
                
                FeatureItem(
                    imageResId = R.drawable.feature_goals,
                    title = stringResource(R.string.feature_goals_title),
                    description = stringResource(R.string.feature_goals_desc)
                )
                
                FeatureItem(
                    imageResId = R.drawable.feature_progress,
                    title = stringResource(R.string.feature_progress_title),
                    description = stringResource(R.string.feature_progress_desc)
                )
                
                FeatureItem(
                    imageResId = R.drawable.feature_reminders,
                    title = stringResource(R.string.feature_reminders_title),
                    description = stringResource(R.string.feature_reminders_desc)
                )
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Get Started Button
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically()
        ) {
            PrimaryButton(
                text = stringResource(R.string.get_started),
                onClick = onGetStarted,
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_lg)))
    }
}

@Composable
private fun FeatureItem(
    imageResId: Int,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.card_padding))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_md)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(R.dimen.image_size_md))
            )
            
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_xs))
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
} 