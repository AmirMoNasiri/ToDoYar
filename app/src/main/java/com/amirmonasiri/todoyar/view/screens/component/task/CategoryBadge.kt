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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amirmonasiri.todoyar.model.Category
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import com.amirmonasiri.todoyar.view.ui.theme.VazirFontFamily

/**
 * Displays the category assigned to a task as a small badge.
 *
 * The badge is hidden when the task has no category.
 * Its appearance changes when the task is completed to
 * match the completed-task visual style.
 *
 * @param category Category associated with the task.
 * @param isCompleted Whether the task has been completed.
 */
@Composable
fun CategoryBadge(
    category: Category?,
    isCompleted: Boolean
) {
    // Nothing to display when the task has no category
    if (category == null) return

    Card(
        shape = RoundedCornerShape(Dimens.CornerBadge),
        colors = CardDefaults.cardColors(
            containerColor = if (isCompleted) {
                Color.Transparent
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }
        )
    ) {
        Text(
            text = category.name,
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
                MaterialTheme.colorScheme.onSecondaryContainer
            }
        )
    }
}