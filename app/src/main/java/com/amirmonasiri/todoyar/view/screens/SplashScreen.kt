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
import com.amirmonasiri.todoyar.BuildConfig
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.navigation.AppScreens
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import com.amirmonasiri.todoyar.view.ui.theme.PersianBlue
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

/**
 * Splash screen displayed when the application starts.
 *
 * Responsibilities:
 * - Displays the application logo and loading indicator.
 * - Provides a short startup delay for branding purposes.
 * - Navigates to the main screen after initialization.
 */
@Composable
fun SplashScreen(navController: NavController) {

    /*
    Navigate to the main screen after a short delay
    and remove SplashScreen from the back stack
    */
    LaunchedEffect(Unit) {
        delay(2000.milliseconds)
        navController.navigate(AppScreens.Main.route) {
            popUpTo(AppScreens.Splash.route) {
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
            painter = painterResource(
                id = R.drawable.ic_todoyar_noback
            ),
            contentDescription = "To Do Yar Icon",
            modifier = Modifier
                .size(Dimens.IconSplash)
                .align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Dimens.BottomSpace),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(Dimens.CPISize),
                color = Color.White,
                strokeWidth = Dimens.CPIStrokeSize
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "نسخه ${BuildConfig.VERSION_NAME}",
                color = Color.White,
            )
        }
    }
}
