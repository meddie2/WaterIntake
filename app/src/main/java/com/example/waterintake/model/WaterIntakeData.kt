package com.example.waterintake.model

import java.util.*

data class WaterIntake(
    val id: Long = 0,
    val amount: Int, // in milliliters
    val timestamp: Date = Date(),
    val type: WaterType = WaterType.GLASS
)

enum class WaterType {
    GLASS,
    BOTTLE,
    CUP
}

data class DailyWaterGoal(
    val targetAmount: Int = 2500, // Default 2.5 liters
    val currentAmount: Int = 0,
    val date: Date = Date()
) 