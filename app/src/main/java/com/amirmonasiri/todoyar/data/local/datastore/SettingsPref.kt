package com.amirmonasiri.todoyar.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Application DataStore instance used to persist user settings.
 */
private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(
            name = SETTINGS_DATASTORE
        )
private const val SETTINGS_DATASTORE = "settings_prefs"
val DARK_THEME = booleanPreferencesKey("dark_theme")


/**
 * Handles reading and writing application settings
 * using Jetpack DataStore.
 *
 * Currently responsible for:
 * - Theme preferences
 */
class SettingsPref @Inject constructor(
    @ApplicationContext private val context: Context
) {
    /**
     * Emits the current dark theme state.
     *
     * Returns:
     * - true  -> Dark theme enabled
     * - false -> Light theme enabled
     */
    val darkTheme: Flow<Boolean> =
        context.dataStore.data.map { prefs ->
            prefs[DARK_THEME] ?: false
        }

    /**
     * Persists the user's theme preference.
     *
     * @param enabled true to enable dark theme,
     * false to use light theme.
     */
    suspend fun setDarkTheme(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DARK_THEME] = enabled
        }
    }

}