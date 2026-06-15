package com.amirmonasiri.todoyar.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirmonasiri.todoyar.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val isDarkTheme = settingsRepository.darkTheme
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setDarkTheme(enabled)
        }
    }
}