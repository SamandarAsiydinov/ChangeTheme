package com.example.jetpacktheme.di

import kotlinx.coroutines.flow.StateFlow

enum class AppTheme {
    MODE_AUTO,
    MODE_DAY,
    MODE_NIGHT;

    companion object {
        fun fromOrdinal(ordinal: Int) = values()[ordinal]
    }
}

interface ThemeSetting {
    val themeStream: StateFlow<AppTheme>
    var theme: AppTheme
}