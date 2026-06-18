package com.amirmonasiri.todoyar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.os.LocaleListCompat
import com.amirmonasiri.todoyar.view.navigation.MainScreen
import com.amirmonasiri.todoyar.view.ui.theme.ToDoYarTheme
import com.amirmonasiri.todoyar.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val darkTheme by mainViewModel.darkTheme.collectAsState()
            val language by mainViewModel.language.collectAsState()
            LaunchedEffect(language) {
                AppCompatDelegate.setApplicationLocales(
                    LocaleListCompat.forLanguageTags(
                        language.languageTag
                    )
                )
            }
            ToDoYarTheme(
                darkTheme = darkTheme,
                language = language
            ) {
                MainScreen()
            }
        }
    }
}
