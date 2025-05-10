package com.example.waterintake.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waterintake.R
import com.example.waterintake.viewmodel.WaterIntakeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackWaterScreen(
    onNavigateBack: () -> Unit,
    viewModel: WaterIntakeViewModel = viewModel()
) {
    var waterAmount by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.track_water_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.daily_goal),
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = stringResource(
                    R.string.current_intake,
                    "${state.currentIntake}ml"
                ),
                style = MaterialTheme.typography.headlineMedium
            )

            if (state.isGoalAchieved) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Text(
                        text = stringResource(R.string.goal_achieved),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            OutlinedTextField(
                value = waterAmount,
                onValueChange = { waterAmount = it },
                label = { Text(stringResource(R.string.water_amount)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        waterAmount.toIntOrNull()?.let { amount ->
                            viewModel.addWater(amount)
                            waterAmount = ""
                        }
                    },
                    modifier = Modifier.weight(1f),
                    enabled = waterAmount.isNotEmpty()
                ) {
                    Text(stringResource(R.string.save))
                }

                OutlinedButton(
                    onClick = { waterAmount = "" },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }

            LinearProgressIndicator(
                progress = (state.currentIntake.toFloat() / state.dailyGoal),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
        }
    }
} 