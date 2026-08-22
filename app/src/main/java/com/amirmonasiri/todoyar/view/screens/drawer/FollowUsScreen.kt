package com.amirmonasiri.todoyar.view.screens.drawer


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.duotone.ArrowRight
import com.adamglin.phosphoricons.duotone.GithubLogo
import com.adamglin.phosphoricons.duotone.InstagramLogo
import com.adamglin.phosphoricons.duotone.LinkedinLogo
import com.adamglin.phosphoricons.duotone.TelegramLogo
import com.adamglin.phosphoricons.duotone.Users


/**
 * Follow Us screen.
 *
 * Displays social media links related to ToDo Yar
 * and allows users to open them using external apps.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowUsScreen(
    navController: NavHostController
) {
    val context = LocalContext.current

    fun openUrl(url: String) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = PhosphorIcons.Duotone.ArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )

            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.height(32.dp))

            Icon(
                imageVector = PhosphorIcons.Duotone.Users,
                contentDescription = null,
                modifier = Modifier.size(72.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(16.dp))

            Text(
                text = "ما را دنبال کنید",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))

            Text(
                text = "برای دریافت آخرین اخبار و به‌روزرسانی‌های تودویار همراه ما باشید.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(32.dp))
            SocialCard(
                title = "گیت هاب",
                icon = {
                    Icon(
                        imageVector = PhosphorIcons.Duotone.GithubLogo,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                },
                onClick = {
                    openUrl("https://github.com/amirmonasiri")
                }
            )
            SocialCard(
                title = "لینکدین",
                icon = {
                    Icon(
                        imageVector = PhosphorIcons.Duotone.LinkedinLogo,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                },
                onClick = {
                    openUrl("https://www.linkedin.com/in/amirmonasir/")
                }
            )

            SocialCard(
                title = "تلگرام",
                icon = {
                    Icon(
                        imageVector = PhosphorIcons.Duotone.TelegramLogo,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                },
                onClick = {
                    openUrl("https://t.me/amirmonasiri")
                }
            )

            SocialCard(
                title = "اینستاگرام",
                icon = {
                    Icon(
                        imageVector = PhosphorIcons.Duotone.InstagramLogo,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                },
                onClick = {
                    openUrl("https://www.instagram.com/amirmonasiri")
                }
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "ساخته شده با ❤️ توسط امیرمحمد نصیری",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )


        }
    }
}

/**
 * Reusable social media card.
 *
 * @param title Social platform name
 * @param icon Platform icon
 * @param onClick Opens the related link
 */
@Composable
private fun SocialCard(
    title: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clip(RoundedCornerShape(18.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            icon()

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}