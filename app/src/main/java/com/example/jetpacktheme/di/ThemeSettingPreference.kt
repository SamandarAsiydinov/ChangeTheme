package com.example.jetpacktheme.di

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ThemeSettingPreference @Inject constructor(
    @ApplicationContext context: Context
) : ThemeSetting {
    override val themeStream: MutableStateFlow<AppTheme>
    override var theme: AppTheme by AppThemePrefDelegate("app_theme", AppTheme.MODE_AUTO)

    private val preference: SharedPreferences =
        context.getSharedPreferences("sample_theme", Context.MODE_PRIVATE)

    init {
        themeStream = MutableStateFlow(theme)
    }

    inner class AppThemePrefDelegate(
        private val name: String,
        private val default: AppTheme
    ) : ReadWriteProperty<Any?, AppTheme> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): AppTheme {
            return AppTheme.fromOrdinal(preference.getInt(name, default.ordinal))
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: AppTheme) {
            themeStream.value = value
            preference.edit {
                putInt(name, value.ordinal)
            }
        }
    }
}

