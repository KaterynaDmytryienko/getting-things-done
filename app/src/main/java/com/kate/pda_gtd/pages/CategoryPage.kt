package com.kate.pda_gtd.pages

import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kate.pda_gtd.components.CategoryCreationDialog
import com.kate.pda_gtd.components.TaskDialogClass
import com.kate.pda_gtd.data.CategoryViewModel
import com.kate.pda_gtd.data.TaskEvent
import com.kate.pda_gtd.data.TaskState
import com.kate.pda_gtd.data.TaskViewModel
import java.time.LocalDateTime

class CategoryPage : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun CategoryPageContent(viewModel: TaskViewModel, categoryName: String) {
        val taskViewModel: TaskViewModel = viewModel()
        val categoryViewModel: CategoryViewModel = viewModel()
        var showDialog by remember { mutableStateOf(false) }

        val viewState = viewModel.state.collectAsState()

        val tasksInCategory = viewState.value.tasks.filter {
            it.category.equals(categoryName, ignoreCase = true)
        }
        if (tasksInCategory.isEmpty()) {
            Text("No tasks in this category", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(100.dp))
        } else {
            LazyColumn(Modifier.padding(top = 56.dp)) {
                items(tasksInCategory) { task ->
                    ListItem(
                        headlineContent = { Text(task.name) },
                        leadingContent = {
                            Checkbox(
                                checked = task.isCompleted,
                                onCheckedChange = { isChecked ->
                                    taskViewModel.onEvent(TaskEvent.DeleteTaskById(task.id))
                                }
                            )
                        }
                    )
                }

            }

        }

        Column(Modifier.padding(top = 650.dp, bottom = 30.dp, start = 300.dp)) {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Task")
            }
        }

        if (showDialog) {
            TaskDialogClass().TaskDialog(
                onDismiss = { showDialog = false },
                onEvent = { event -> taskViewModel.onEvent(event) },
                categoryViewModel = categoryViewModel,
                pageName = "category/$categoryName"
            )
        }
    }
}