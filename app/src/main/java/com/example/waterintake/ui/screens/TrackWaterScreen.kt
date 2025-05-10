package com.example.waterintake.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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
                title = { Text(text = stringResource(R.string.track_water)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.screen_padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_medium))
        ) {
            OutlinedTextField(
                value = waterAmount,
                onValueChange = { waterAmount = it },
                label = { Text(text = stringResource(R.string.water_amount)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    waterAmount.toIntOrNull()?.let { amount ->
                        viewModel.addWaterIntake(amount)
                        waterAmount = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.add_water))
            }

            LinearProgressIndicator(
                progress = (state.currentIntake.toFloat() / state.dailyGoal),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.progress_indicator_padding))
            )
        }
    }
} 