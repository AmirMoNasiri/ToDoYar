package com.amirmonasiri.todoyar.view.screens.component.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amirmonasiri.todoyar.view.ui.theme.Dimens

/**
 * Displays the overall task completion progress.
 *
 * Shows a linear progress indicator based on the ratio of completed
 * tasks to total tasks, along with the corresponding percentage value.
 *
 * @param completionRate Completion progress as a value between 0f and 1f.
 */
@Composable
fun CompletionSection(
    completionRate: Float
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimens.Corner),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = "نرخ تکمیل وظایف",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { completionRate },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Text("${(completionRate * 100).toInt()}٪")
        }
    }
}