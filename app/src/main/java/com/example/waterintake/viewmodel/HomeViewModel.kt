package com.example.waterintake.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.waterintake.model.WaterIntake
import com.example.waterintake.model.DailyWaterGoal
import com.example.waterintake.model.WaterType
import java.util.*
import kotlin.math.min

data class HomeUiState(
    val dailyGoal: DailyWaterGoal = DailyWaterGoal(),
    val recentIntakes: List<WaterIntake> = emptyList(),
    val totalIntakeToday: Int = 0,
    val progressPercentage: Float = 0f,
    val lastUpdated: Date = Date()
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun updateDailyGoal(targetAmount: Int) {
        if (targetAmount <= 0) return
        
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            dailyGoal = currentState.dailyGoal.copy(targetAmount = targetAmount),
            progressPercentage = calculateProgressPercentage(
                currentState.totalIntakeToday,
                targetAmount
            )
        )
    }

    fun addWaterIntake(amount: Int, type: WaterType) {
        if (amount <= 0) return
        
        val currentState = _uiState.value
        val newIntake = WaterIntake(
            amount = amount,
            type = type,
            timestamp = Date()
        )
        
        // Filter intakes for today only
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time
        
        val todayIntakes = currentState.recentIntakes.filter { 
            it.timestamp.after(today) || it.timestamp == today
        }
        
        val updatedIntakes = (todayIntakes + newIntake)
            .sortedByDescending { it.timestamp }
            .take(5) // Keep only the 5 most recent entries
        
        val newTotalIntake = min(currentState.totalIntakeToday + amount, Int.MAX_VALUE)
        
        _uiState.value = currentState.copy(
            recentIntakes = updatedIntakes,
            totalIntakeToday = newTotalIntake,
            progressPercentage = calculateProgressPercentage(
                newTotalIntake,
                currentState.dailyGoal.targetAmount
            ),
            lastUpdated = Date()
        )
    }

    private fun calculateProgressPercentage(current: Int, target: Int): Float {
        return if (target > 0) {
            (current.toFloat() / target.toFloat()).coerceIn(0f, 1f)
        } else {
            0f
        }
    }

    fun resetDailyProgress() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            totalIntakeToday = 0,
            progressPercentage = 0f,
            lastUpdated = Date()
        )
    }
} 