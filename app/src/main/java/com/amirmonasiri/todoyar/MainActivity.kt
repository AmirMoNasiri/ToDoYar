package com.amirmonasiri.todoyar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.amirmonasiri.todoyar.view.navigation.toDoYarNavHost

import com.amirmonasiri.todoyar.view.ui.theme.ToDoYarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoYarTheme {
                toDoYarNavHost()
            }
        }
    }
}
