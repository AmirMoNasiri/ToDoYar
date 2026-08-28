package com.amirmonasiri.todoyar.view.screens.component.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Displays a donut chart representing the distribution of
 * active tasks across different categories.
 *
 * The chart visualizes the percentage of tasks assigned to each category,
 * while the legend shows the category name and task count.
 *
 * If no tasks are available, an empty-state message is displayed instead.
 *
 * @param categoryCounts A map where the key is the category name and
 * the value is the number of tasks belonging to that category.
 */
@Composable
fun CategoryDonutChart(
    categoryCounts: Map<String, Int>
) {
    val totalTasks = categoryCounts.values.sum()

    if (totalTasks == 0) {
        Text("هیچ وظیفه‌ای ثبت نشده")
        return
    }
    val categoryColors = listOf(
        Color(0xFF327AFA),
        Color(0xFF6FCF97),
        Color(0xFFF2C94C),
        Color(0xFFFF8A65),
        Color(0xFFBA68C8),
        Color(0xFF26C6DA),
        Color(0xFFEC407A),
        Color(0xFF2D8035),
        Color(0xFFFF7043),
        Color(0xFFC0FD32),
        Color(0xFFBD49C1),
        Color(0xFFF44336)
    )
    val categories = categoryCounts.entries.toList()

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "وظایف بر اساس دسته‌بندی",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {

                    Canvas(modifier = Modifier.size(150.dp)) {
                        val stroke = 26.dp.toPx()

                        var startAngle = -90f

                        categories.forEachIndexed { index, item ->

                            val sweep = (item.value.toFloat() / totalTasks) * 360f

                            drawArc(
                                color = categoryColors[index % categoryColors.size],
                                startAngle = startAngle,
                                sweepAngle = sweep,
                                useCenter = false,
                                style = Stroke(
                                    width = stroke,
                                    cap = StrokeCap.Round
                                )
                            )

                            startAngle += sweep
                        }


                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = totalTasks.toString(),
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Text(
                            text = "وظیفه",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val categories =
                        categoryCounts.entries.toList()

                    val colors = listOf(
                        Color(0xFF4F8EF7),
                        Color(0xFF6FCF97),
                        Color(0xFFF2C94C),
                        Color(0xFFE57373),
                        Color(0xFFBA68C8)
                    )

                    categories.forEachIndexed { index, item ->

                        CategoryLegend(
                            color = colors[index % colors.size],
                            title = item.key,
                            count = item.value
                        )
                    }
                }
            }
        }
    }
}

/**
 * Displays a single legend item used by the category donut chart.
 *
 * Each legend item consists of:
 * - A colored indicator matching the chart segment
 * - The category name
 * - The number of tasks in that category
 *
 * @param color Color associated with the category segment.
 * @param title Category name.
 * @param count Number of tasks in the category.
 */
@Composable
private fun CategoryLegend(
    color: Color,
    title: String,
    count: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.width(6.dp))

        Text(
            text = "($count)",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}
