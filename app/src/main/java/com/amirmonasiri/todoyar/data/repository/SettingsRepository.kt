package com.amirmonasiri.todoyar.data.repository

import kotlinx.coroutines.flow.Flow

/**
 * Contract for application settings operations.
 *
 * Provides access to user preferences and settings
 * independent of the underlying storage implementation.
 */
interface SettingsRepository {

    /**
     * Emits the current theme preference.
     *
     * true  -> Dark theme
     * false -> Light theme
     */
    val darkTheme: Flow<Boolean>

    /**
     * Persists the selected theme mode.
     *
     * @param enabled true to enable dark theme,
     * false to use light theme.
     */
    suspend fun setDarkTheme(enabled: Boolean)

}