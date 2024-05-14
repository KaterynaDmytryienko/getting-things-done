package com.kate.pda_gtd.pages

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kate.pda_gtd.components.TaskDialogClass
import com.kate.pda_gtd.data.CategoryViewModel
import com.kate.pda_gtd.data.TaskEvent
import com.kate.pda_gtd.data.TaskState
import com.kate.pda_gtd.data.TaskViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class TodayPage: ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(date: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
        return date.format(formatter)
    }
    @SuppressLint("StateFlowValueCalledInComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun TodayPageContent(state: TaskState) {

        val taskViewModel: TaskViewModel = viewModel()
        val categoryViewModel : CategoryViewModel = viewModel()
        var showDialog by remember { mutableStateOf(false) }

        val tasksInTodayPage = taskViewModel.state.value.tasks
        if(tasksInTodayPage.isEmpty()){
            Text("No tasks here yet", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(100.dp, 100.dp))
        }
        LazyColumn (Modifier.padding(top = 56.dp)){
            items(taskViewModel.state.value.tasks) { task ->
                if(task.dueDate == formatDate(LocalDateTime.now())){
                    ListItem(
                        headlineContent = { Text(task.name) },
                        leadingContent = {
                            Checkbox(
                                checked = task.isCompleted,
                                onCheckedChange = { isChecked ->
                                    taskViewModel.onEvent(TaskEvent.DeleteTaskById(task.id))
                                }
                            )
                        })
                }

            }
        }
  Column(Modifier.padding(top = 650.dp, bottom = 30.dp, start = 300.dp)) {
      FloatingActionButton(onClick = { showDialog = true }) {
          Icon(Icons.Filled.Add, contentDescription = "Add Task")
      }
  }

        if (showDialog) {
            TaskDialogClass().TaskDialog(onDismiss = { showDialog = false }, onEvent = { event -> taskViewModel.onEvent(event)}, categoryViewModel = categoryViewModel, pageName = "today", dateSelected = "")
        }
    }
}