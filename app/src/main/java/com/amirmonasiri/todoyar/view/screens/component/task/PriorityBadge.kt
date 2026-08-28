package com.amirmonasiri.todoyar.view.screens.component.task

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.model.TaskPriority
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import com.amirmonasiri.todoyar.view.ui.theme.VazirFontFamily

/**
 * Displays a visual badge representing a task's priority level.
 *
 * The badge text is localized based on the provided priority and
 * its appearance changes depending on the task completion state.
 *
 * When the task is completed, the badge is displayed with a muted style.
 * Otherwise, it uses the application's primary container colors.
 *
 * @param priority Priority level of the task.
 * @param isCompleted Whether the task has been completed.
 */
@Composable
fun PriorityBadge(
    priority: TaskPriority,
    isCompleted: Boolean
) {

    val priorityLabel = when (priority) {
        TaskPriority.LOW -> stringResource(R.string.priority_low)
        TaskPriority.MEDIUM -> stringResource(R.string.priority_medium)
        TaskPriority.HIGH -> stringResource(R.string.priority_high)
        TaskPriority.URGENT -> stringResource(R.string.priority_urgent)
    }

    Card(
        shape = RoundedCornerShape(Dimens.CornerBadge),
        colors = CardDefaults.cardColors(
            containerColor =
                if (isCompleted) {
                    Color.Transparent
                } else {
                    MaterialTheme.colorScheme.primaryContainer
                }
        )
    ) {

        Text(
            text = priorityLabel,
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 5.dp
            ),
            fontFamily = VazirFontFamily,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = if (isCompleted) {
                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.65f)
            } else {
                MaterialTheme.colorScheme.onPrimaryContainer
            }
        )
    }
}