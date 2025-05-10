package com.example.waterintake.model

import java.util.Date

data class AppState(
    val currentScreen: Screen = Screen.Welcome,
    val waterIntakes: List<WaterIntake> = emptyList(),
    val dailyGoal: DailyWaterGoal = DailyWaterGoal(),
    val lastUpdated: Date = Date()
)

sealed class UiEvent {
    data class AddWaterIntake(val amount: Int, val type: WaterType) : UiEvent()
    data class UpdateDailyGoal(val targetAmount: Int) : UiEvent()
    data class NavigateTo(val screen: Screen) : UiEvent()
    object NavigateBack : UiEvent()
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val appState: AppState) : UiState()
    data class Error(val message: String) : UiState()
} 