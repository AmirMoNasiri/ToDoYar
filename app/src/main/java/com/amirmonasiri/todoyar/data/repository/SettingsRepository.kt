package com.amirmonasiri.todoyar.data.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val darkTheme: Flow<Boolean>

    suspend fun setDarkTheme(enabled: Boolean)

}