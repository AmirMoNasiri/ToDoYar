package com.amirmonasiri.todoyar.view.screens.component.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.view.ui.theme.Dimens

/**
 * Displays the user's profile summary section.
 *
 * Contains the profile picture, title and a short action message.
 * This component serves as the header of the profile screen.
 *
 * a placeholder state is shown until user authentication
 * and profile information are implemented.
 */
@Composable
fun ProfileHeader() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimens.Corner),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.default_profile),
                contentDescription = "profile picture",
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),

                ) {
                Text(
                    text = "پروفایل",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "این قسمت در نسخه های آینده در دسترس خواهد بود",
                    style = MaterialTheme.typography.bodyMedium
                )
            }


        }
    }
}