package com.amirmonasiri.todoyar.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.amirmonasiri.todoyar.utils.AppLanguage
import com.amirmonasiri.todoyar.utils.Constants
import com.amirmonasiri.todoyar.utils.PreferenceKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.SETTINGS_DATASTORE)

class SettingsPref @Inject constructor(
    @ApplicationContext private val context: Context
) {

    val darkTheme: Flow<Boolean> =
        context.dataStore.data.map { prefs ->
            prefs[PreferenceKeys.DARK_THEME] ?: false
        }

    val language: Flow<AppLanguage> =
        context.dataStore.data.map { prefs ->
            AppLanguage.valueOf(
                prefs[PreferenceKeys.LANGUAGE]
                    ?: AppLanguage.ENGLISH.name
            )
        }


    suspend fun setDarkTheme(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[PreferenceKeys.DARK_THEME] = enabled
        }
    }

    suspend fun setLanguage(language: AppLanguage) {
        context.dataStore.edit { prefs ->
            prefs[PreferenceKeys.LANGUAGE] = language.name
        }
    }
}