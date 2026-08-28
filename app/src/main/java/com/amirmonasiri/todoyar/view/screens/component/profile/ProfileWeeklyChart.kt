package com.amirmonasiri.todoyar.view.screens.component.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amirmonasiri.todoyar.view.ui.theme.Dimens

/**
 * Displays the weekly activity section on the profile screen.
 *
 * Shows a bar chart representing the number of completed tasks
 * for each day of the last seven days.
 *
 * @param weekData A list containing activity counts for each day
 * of the week, ordered from Saturday to Friday.
 */
@Composable
fun ProfileWeeklyChart(weekData: List<Int>) {

    val weekLabels = listOf("ش", "ی", "د", "س", "چ", "پ", "ج")

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimens.Corner),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "فعالیت هفتگی",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "۷ روز گذشته",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(24.dp))

            WeeklyBarChart(
                data = weekData,
                labels = weekLabels
            )
        }
    }
}