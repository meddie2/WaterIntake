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
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackWaterScreen(
    onNavigateBack: () -> Unit,
    viewModel: WaterIntakeViewModel = viewModel()
) {
    var waterAmount by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.track_water_title)) },
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
                onValueChange = { 
                    waterAmount = it
                    showError = false
                },
                label = { Text(text = stringResource(R.string.water_amount)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = showError,
                supportingText = if (showError) {
                    { Text(text = stringResource(R.string.invalid_amount)) }
                } else null
            )

            Button(
                onClick = {
                    val amount = waterAmount.toIntOrNull()
                    if (amount != null && amount > 0) {
                        viewModel.addWaterIntake(amount)
                        waterAmount = ""
                        showError = false
                    } else {
                        showError = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.save))
            }

            LinearProgressIndicator(
                progress = if (state.dailyGoal > 0) {
                    (state.currentIntake.toFloat() / state.dailyGoal).coerceIn(0f, 1f)
                } else 0f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.progress_indicator_padding))
            )
        }
    }
} 