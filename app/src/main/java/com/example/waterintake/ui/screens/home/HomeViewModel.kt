package com.example.waterintake.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waterintake.model.WaterIntake
import com.example.waterintake.model.WaterType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val dailyGoal: Int = 2000,
        val currentIntake: Int = 0,
        val remaining: Int = 2000,
        val recentIntakes: List<WaterIntake> = emptyList()
    ) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                // TODO: Load data from repository
                // For now, using sample data
                val sampleIntakes = listOf(
                    WaterIntake(250, WaterType.PLAIN, Date()),
                    WaterIntake(300, WaterType.TEA, Date()),
                    WaterIntake(200, WaterType.JUICE, Date())
                )
                
                val totalIntake = sampleIntakes.sumOf { it.amount }
                val dailyGoal = 2000 // TODO: Load from settings
                
                _uiState.update {
                    HomeUiState.Success(
                        dailyGoal = dailyGoal,
                        currentIntake = totalIntake,
                        remaining = dailyGoal - totalIntake,
                        recentIntakes = sampleIntakes
                    )
                }
            } catch (e: Exception) {
                _uiState.update { HomeUiState.Error(e.message ?: "Unknown error occurred") }
            }
        }
    }

    fun refresh() {
        loadData()
    }
} 