package com.example.waterintake.model

object AppResources {
    // App Strings
    object Strings {
        const val APP_NAME = "Water Intake Uganda"
        const val WELCOME_MESSAGE = "Welcome to Water Intake Uganda"
        const val TRACK_WATER_TITLE = "Track Your Water Intake"
        const val DAILY_GOAL = "Daily Goal: %d ml"
        const val CURRENT_INTAKE = "Current Intake: %d ml"
        const val ADD_WATER = "Add Water"
        const val WATER_AMOUNT = "Amount (ml)"
        const val SAVE = "Save"
        const val CANCEL = "Cancel"
        const val GET_STARTED = "Get Started"
        const val WATER_ADDED = "Water intake recorded!"
        const val GOAL_ACHIEVED = "Congratulations! You've reached your daily goal!"
        const val KEEP_GOING = "Keep going! You're doing great!"
        
        // Navigation
        const val NAV_HOME = "Home"
        const val NAV_TRACK = "Track Water"
        
        // Water Importance Messages
        const val WATER_IMPORTANCE_TITLE = "Why Water Matters"
        const val WATER_IMPORTANCE_1 = "Stay Hydrated: Drinking enough water helps maintain your body's fluid balance."
        const val WATER_IMPORTANCE_2 = "Boost Energy: Proper hydration can increase your energy levels and improve your mood."
        const val WATER_IMPORTANCE_3 = "Improve Health: Water is essential for digestion, absorption, and circulation."
        const val WATER_IMPORTANCE_4 = "Support Digestion: Water helps break down food and keeps your digestive system running smoothly."
        const val WATER_IMPORTANCE_5 = "Maintain Temperature: Water helps regulate your body temperature, especially during physical activity."
    }

    // App Colors
    object Colors {
        const val PRIMARY = "#2196F3"
        const val PRIMARY_LIGHT = "#64B5F6"
        const val PRIMARY_DARK = "#1976D2"
        const val ACCENT = "#03A9F4"
        const val BACKGROUND = "#FFFFFF"
        const val SURFACE = "#F5F5F5"
        const val ERROR = "#B00020"
        const val ON_PRIMARY = "#FFFFFF"
        const val ON_BACKGROUND = "#000000"
        const val ON_SURFACE = "#000000"
        const val ON_ERROR = "#FFFFFF"
    }

    // App Dimensions
    object Dimensions {
        const val PADDING_SMALL = 8
        const val PADDING_MEDIUM = 16
        const val PADDING_LARGE = 24
        const val BUTTON_HEIGHT = 56
        const val ICON_SIZE = 24
        const val LARGE_ICON_SIZE = 48
    }

    // Water Intake Constants
    object WaterIntake {
        const val DEFAULT_GOAL = 2500 // 2.5 liters in ml
        const val SMALL_GLASS = 200 // ml
        const val MEDIUM_GLASS = 300 // ml
        const val LARGE_GLASS = 400 // ml
        const val BOTTLE = 500 // ml
        const val CUP = 250 // ml
    }
} 