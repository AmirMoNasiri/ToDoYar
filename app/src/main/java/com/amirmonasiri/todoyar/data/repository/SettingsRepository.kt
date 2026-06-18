package com.amirmonasiri.todoyar.data.repository

import com.amirmonasiri.todoyar.utils.AppLanguage
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val darkTheme: Flow<Boolean>
    val language: Flow<AppLanguage>

    suspend fun setDarkTheme(enabled: Boolean)
    suspend fun setLanguage(language: AppLanguage)
}