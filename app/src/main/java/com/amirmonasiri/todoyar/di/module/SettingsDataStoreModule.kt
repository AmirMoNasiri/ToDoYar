package com.amirmonasiri.todoyar.di.module

import android.content.Context
import com.amirmonasiri.todoyar.data.local.datastore.SettingsPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsDataStoreModule {

    @Provides
    @Singleton
    fun provideSettingsPref(
        @ApplicationContext context: Context
    ): SettingsPref {
        return SettingsPref(context)
    }
}