package com.kate.pda_gtd.pages

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kate.pda_gtd.components.NotificationService
import com.kate.pda_gtd.data.TaskState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class InboxPage() {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun InboxPageContent(state: TaskState, context: Context) {
        val formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
        val today = LocalDate.now()

        LazyColumn(Modifier.padding(top = 56.dp)) {
            items(state.tasks) { task ->
                    val taskDate = LocalDate.parse(task.dueDate, formatter)
                    val daysBetween = ChronoUnit.DAYS.between(today, taskDate)
                    ListItem(
                        headlineContent = { Text(task.name) },
                        trailingContent = { AssistChip(onClick = {}, label = { Text("$daysBetween days") }) }
                    )
                }
            }
        }
    }
