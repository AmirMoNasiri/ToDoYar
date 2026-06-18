package com.amirmonasiri.todoyar.data.repository

import com.amirmonasiri.todoyar.data.local.datastore.SettingsPref
import com.amirmonasiri.todoyar.utils.AppLanguage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val settingsPref: SettingsPref
) : SettingsRepository {

    override val darkTheme: Flow<Boolean> =
        settingsPref.darkTheme

    override val language: Flow<AppLanguage> =
        settingsPref.language

    override suspend fun setDarkTheme(enabled: Boolean) {
        settingsPref.setDarkTheme(enabled)
    }

    override suspend fun setLanguage(language: AppLanguage) {
        settingsPref.setLanguage(language)
    }
}