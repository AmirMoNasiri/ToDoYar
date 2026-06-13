package com.amirmonasiri.todoyar.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val DARK_THEME = booleanPreferencesKey("dark_theme")
    val LANGUAGE = stringPreferencesKey("language")
}