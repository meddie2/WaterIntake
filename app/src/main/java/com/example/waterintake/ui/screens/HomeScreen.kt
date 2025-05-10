package com.example.waterintake.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.waterintake.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToTrackWater: () -> Unit
) {
    val waterImportanceItems = listOf(
        R.string.water_importance_1 to R.drawable.ic_hydration,
        R.string.water_importance_2 to R.drawable.ic_energy,
        R.string.water_importance_3 to R.drawable.ic_health, // This string content seems to overlap with others, but keeping the original mapping for now
        R.string.water_importance_4 to R.drawable.ic_digestion,
        R.string.water_importance_5 to R.drawable.ic_temperature,
        R.string.water_importance_6 to R.drawable.ic_skin_health, // Changed to better match the string content
        R.string.water_importance_7 to R.drawable.ic_water_kidney,
        R.string.water_importance_8 to R.drawable.ic_weight_loss, // Added for the new string
        R.string.water_importance_9 to R.drawable.ic_digestion, // Matches water_importance_4 theme
        R.string.water_importance_10 to R.drawable.ic_energy, // Matches water_importance_2 theme
        R.string.water_importance_11 to R.drawable.ic_health, // Matches water_importance_3 theme
        R.string.water_importance_12 to R.drawable.ic_hydration, // Matches water_importance_1 theme
        R.string.water_importance_13 to R.drawable.ic_water_joint, // Added for the new string
        R.string.water_importance_14 to R.drawable.ic_water_kidney // Matches water_importance_7 theme
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.welcome_message),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Text(
                    text = stringResource(R.string.water_importance_title),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(waterImportanceItems) { (textRes, iconRes) ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = stringResource(textRes),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            item {
                Button(
                    onClick = onNavigateToTrackWater,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text(stringResource(R.string.add_water))
                }
            }
        }
    }
} 