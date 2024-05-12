package com.kate.pda_gtd.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kate.pda_gtd.data.CategoryDao
import com.kate.pda_gtd.data.CategoryEvent
import com.kate.pda_gtd.data.CategoryViewModel
import com.kate.pda_gtd.data.TaskEvent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class TaskDialogClass {
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskDialog(onDismiss: () -> Unit, onEvent: (TaskEvent)->Unit, categoryViewModel: CategoryViewModel, pageName:String) {
    var expanded by remember { mutableStateOf(false) }

    var selectedCategory by remember { mutableStateOf("Select a category") }
    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerDialog = DatePickerDialog()
    val timePickerDialog = TimePickerDialog()
    val context = LocalContext.current
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("") }

    val service = NotificationService(context)

    val categories by categoryViewModel.categories.collectAsState()
  if (pageName == "today") {
        selectedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("d/M/yyyy"))
      onEvent(TaskEvent.SetDueDate(selectedDate))
    }
    if (showDatePicker && selectedDate.isEmpty()) {
        showDatePicker = false
        datePickerDialog.showDatePicker(
            context = context,
            onDateSelected = { date ->
                    selectedDate = date.format(DateTimeFormatter.ofPattern("d/M/yyyy"))
                onEvent(TaskEvent.SetDueDate(selectedDate))
            },
            onDismiss = {
                onDismiss()
            }
        )
    }

    if (showTimePicker) {
        showTimePicker = false
        timePickerDialog.showTimePicker(
            context = context,
            onTimeSelected = { time ->
                selectedTime = time
                onEvent(TaskEvent.SetNotificationTime(selectedTime))
            },
            onDismiss = {
                onDismiss()
            }
        )
    }

    if(pageName.startsWith("category")){
        selectedCategory = pageName.split("/").last()
        onEvent(TaskEvent.SetCategory(selectedCategory))
    }


    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = taskName,
                    onValueChange = {
                                onEvent(TaskEvent.SetTaskName(it))
                        taskName = it
                    },
                    label = { Text("Task name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = taskDescription,
                    onValueChange = {
                        onEvent(TaskEvent.SetTaskDescription(it))
                        taskDescription = it
                    },
                    label = { Text("Task description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    OutlinedButton(onClick = {
                        showDatePicker = true

                    }) {

                        Text(if (selectedDate.isEmpty()) "Date" else selectedDate)
                    }
                    OutlinedButton(onClick = {
                        showTimePicker = true
                    }) {
                        Text(selectedTime.ifEmpty { "Notify" })
                    }
                    OutlinedButton(onClick = { /* Handle location */ }) {
                        Text("Geo")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = { expanded = true },
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Text(text = selectedCategory)
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Dropdown"
                    )
                }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            categories.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text(text = category.name) },
                                    onClick = {
                                        selectedCategory = category.name
                                        expanded = false
                                        onEvent(TaskEvent.SetCategory(selectedCategory))
                                    }
                                )
                            }
                        }
                    }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    TextButton(onClick = {
                        if (selectedDate.isNotEmpty()) {
                            if (selectedTime.isNotEmpty()) {
                                service.scheduleNotification(
                                    context,
                                    taskName,
                                    selectedDate,
                                    selectedTime
                                )
                            }
                        }
                        onEvent(TaskEvent.SaveTask(taskName, taskDescription, selectedCategory, selectedDate, selectedTime, false ))
                        onDismiss()
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}



