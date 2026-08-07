package com.amirmonasiri.todoyar.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.navigation.Screens
import com.amirmonasiri.todoyar.view.ui.theme.PersianBlue
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Screens.Main.route) {
            popUpTo(Screens.Splash.route) {
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PersianBlue),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_todoyar_noback),
            contentDescription = "To Do Yar Icon",
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp), color = Color.White, strokeWidth = 3.dp
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "نسخه 1.0.0",
                color = Color.White,
            )
        }
    }
}
