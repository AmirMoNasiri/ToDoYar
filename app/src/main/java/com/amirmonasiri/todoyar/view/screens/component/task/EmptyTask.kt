package com.amirmonasiri.todoyar.view.screens.component.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.bold.CheckCircle
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import com.amirmonasiri.todoyar.view.ui.theme.VazirFontFamily

/**
 * Empty state shown when no tasks are available.
 *
 * Displays an illustration and a short message encouraging
 * the user to create their first task.
 */
@Composable
fun EmptyTasks() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector =
                    PhosphorIcons.Bold.CheckCircle,
                contentDescription = null,
                modifier = Modifier.size(Dimens.IconLarge),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.empty_tasks_title),
                fontFamily = VazirFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.empty_tasks_subtitle),
                fontFamily = VazirFontFamily,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}