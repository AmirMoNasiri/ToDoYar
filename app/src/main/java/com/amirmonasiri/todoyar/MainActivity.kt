package com.amirmonasiri.todoyar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.amirmonasiri.todoyar.view.navigation.navigationBarScreen
import com.amirmonasiri.todoyar.view.navigation.setupNavigation
import com.amirmonasiri.todoyar.view.ui.theme.ToDoYarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoYarTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        navigationBarScreen(navController)
                    }
                ) { innerPadding ->
                    setupNavigation(navController, innerPadding)
                }
            }
        }
    }
}
