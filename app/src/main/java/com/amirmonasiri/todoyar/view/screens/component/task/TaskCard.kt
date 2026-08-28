package com.amirmonasiri.todoyar.view.screens.component.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.duotone.CalendarBlank
import com.adamglin.phosphoricons.duotone.CheckCircle
import com.adamglin.phosphoricons.duotone.Clock
import com.amirmonasiri.todoyar.model.Category
import com.amirmonasiri.todoyar.model.Task
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import com.amirmonasiri.todoyar.view.ui.theme.VazirFontFamily

/**
 * Displays a task item inside a card.
 *
 * Shows the task title, description, due date, due time,
 * category, priority, and completion state.
 *
 * Users can:
 * - Open the task details by clicking the card.
 * - Toggle the completion state using the check icon.
 *
 * @param task Task data to display.
 * @param category Category associated with the task.
 * @param onCompletedChanged Called when the completion state changes.
 * @param onClick Called when the card is selected.
 */
@Composable
fun TaskCard(
    task: Task,
    category: Category?,
    onCompletedChanged: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.Corner))
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(Dimens.Corner),
        colors = CardDefaults.cardColors(
            containerColor = if (task.isCompleted) {
                MaterialTheme.colorScheme.surfaceVariant
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.DefaultElevation)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Completion toggle button
            Icon(
                imageVector =
                    PhosphorIcons.Duotone.CheckCircle,
                contentDescription = if (task.isCompleted) {
                    "انجام شده"
                } else {
                    "انجام نشده"
                },
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()

                        }) {
                        onCompletedChanged(!task.isCompleted)
                    },
                tint =
                    if (task.isCompleted)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Task title and description
            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = task.title,
                    fontFamily = VazirFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (task.isCompleted) {
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.65f)
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                    textDecoration =
                        if (task.isCompleted)
                            TextDecoration.LineThrough
                        else
                            TextDecoration.None
                )

                if (!task.description.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = task.description,
                        fontFamily = VazirFontFamily,
                        fontSize = 13.sp,
                        color = if (task.isCompleted) {
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.65f)
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                        maxLines = 2,
                        textDecoration = if (task.isCompleted) {
                            TextDecoration.LineThrough
                        } else {
                            TextDecoration.None
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Due date and time information
                Row(verticalAlignment = Alignment.CenterVertically) {

                    task.dueDate?.let { date ->

                        Icon(
                            imageVector =
                                PhosphorIcons.Duotone.CalendarBlank,
                            contentDescription = null,
                            modifier = Modifier.size(Dimens.IconSmall),
                            tint =
                                if (task.isCompleted) {
                                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                } else {
                                    MaterialTheme.colorScheme.primary
                                }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${date.year}/${date.month}/${date.day}",
                            fontFamily = VazirFontFamily,
                            fontSize = 12.sp,
                            color =
                                if (task.isCompleted) {
                                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.55f)
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                }
                        )
                    }

                    task.dueTime?.let { time ->
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(
                            imageVector =
                                PhosphorIcons.Duotone.Clock,
                            contentDescription = null,
                            modifier = Modifier.size(Dimens.IconSmall),
                            tint =
                                if (task.isCompleted) {
                                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                } else {
                                    MaterialTheme.colorScheme.primary
                                }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = time.toString(),
                            fontFamily = VazirFontFamily,
                            fontSize = 12.sp,
                            color =
                                if (task.isCompleted) {
                                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.55f)
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))
            // Category and priority badges
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryBadge(
                    category = category,
                    isCompleted = task.isCompleted
                )

                PriorityBadge(
                    priority = task.priority,
                    isCompleted = task.isCompleted
                )
            }
        }
    }
}