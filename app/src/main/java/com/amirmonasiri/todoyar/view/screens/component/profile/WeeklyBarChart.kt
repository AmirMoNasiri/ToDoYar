package com.amirmonasiri.todoyar.view.screens.component.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.amirmonasiri.todoyar.view.ui.theme.Dimens

/**
 * Displays a simple weekly activity bar chart.
 *
 * Each bar represents the number of completed tasks for a specific day
 * of the week. The tallest bar corresponds to the highest activity value,
 * while smaller bars are scaled proportionally.
 *
 * @param data Activity values for each day of the week.
 * @param labels Labels corresponding to each activity value.
 */
@Composable
fun WeeklyBarChart(
    data: List<Int>,
    labels: List<String>
) {

    val maxValue = maxOf(data.maxOrNull() ?: 0, 1)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {

        data.zip(labels).forEach { (value, label) ->

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                val barHeight =
                    if (value == 0) {
                        8.dp
                    } else {
                        ((150f * value) / maxValue).dp
                    }

                Text(
                    text = value.toString(),
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(barHeight)
                        .clip(
                            RoundedCornerShape(
                                topStart = Dimens.Corner,
                                topEnd = Dimens.Corner
                            )
                        )
                        .background(
                            if (value == maxValue)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                        )
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}