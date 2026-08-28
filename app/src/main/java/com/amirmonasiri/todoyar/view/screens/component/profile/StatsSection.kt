package com.amirmonasiri.todoyar.view.screens.component.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amirmonasiri.todoyar.view.ui.theme.Dimens

/**
 * Displays task statistics cards on the profile screen.
 *
 * Shows the number of completed and pending tasks
 * in two separate statistic cards.
 *
 * @param completed Number of completed tasks.
 * @param pending Number of pending tasks.
 */
@Composable
fun StatsSection(
    completed: Int,
    pending: Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),

        ) {

        StatCard(
            modifier = Modifier.weight(1f),
            title = "انجام شده",
            value = completed.toString(),
        )

        StatCard(
            modifier = Modifier.weight(1f),
            title = "در انتظار",
            value = pending.toString()
        )
    }
}

/**
 * Reusable statistics card used in the profile screen.
 *
 * Displays a title and a numeric value inside a Material card.
 *
 * @param modifier Modifier used to control card size and layout.
 * @param title Label displayed below the value.
 * @param value Main statistic value displayed in the card.
 */
@Composable
private fun StatCard(
    modifier: Modifier,
    title: String,
    value: String
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(Dimens.Corner),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(6.dp))

            Text(text = title)
        }
    }
}