package com.example.waterintake.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.min

data class WaterIntakeState(
    val currentIntake: Int = 0,
    val dailyGoal: Int = 2500, // 2.5 liters in ml
    val isGoalAchieved: Boolean = false
)

class WaterIntakeViewModel : ViewModel() {
    private val _state = MutableStateFlow(WaterIntakeState())
    val state: StateFlow<WaterIntakeState> = _state.asStateFlow()

    fun addWaterIntake(amount: Int) {
        if (amount <= 0) return
        
        _state.update { currentState ->
            val newIntake = min(currentState.currentIntake + amount, Int.MAX_VALUE)
            val isGoalAchieved = newIntake >= currentState.dailyGoal
            currentState.copy(
                currentIntake = newIntake,
                isGoalAchieved = isGoalAchieved
            )
        }
    }

    fun resetIntake() {
        _state.update { it.copy(currentIntake = 0, isGoalAchieved = false) }
    }
} 