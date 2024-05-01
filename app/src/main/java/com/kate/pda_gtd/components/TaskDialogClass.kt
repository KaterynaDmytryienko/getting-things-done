package com.kate.pda_gtd.components

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


class TaskDialogClass {
@Composable
fun TaskDialog(onDismiss: () -> Unit) {
    val focusManager = LocalFocusManager.current
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

    if (showDatePicker) {
        datePickerDialog.showDatePicker(
            context = context,
            onDateSelected = { date ->
                selectedDate = date
                showDatePicker = false
            },
            onDismiss = {
                showDatePicker = false
                onDismiss()
            }
        )
    }

    if (showTimePicker) {
        timePickerDialog.showTimePicker(
            context = context,
            onTimeSelected = { time ->
                selectedTime = time
                showTimePicker = false
            },
            onDismiss = {
                showTimePicker = false
                onDismiss()
            }
        )
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
                    onValueChange = { newText ->
                        taskName = newText
                    },
                    label = { Text("Task name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = taskDescription,
                    onValueChange = {newText->
                        taskDescription = newText
                    },
                    label = { Text("Task description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    OutlinedButton(onClick = { showDatePicker = true }) {
                        Text(if (selectedDate.isNotEmpty()) selectedDate else "Date")
                    }
                    OutlinedButton(onClick = {
                        showTimePicker = true
                    }) {
                        Text(if (selectedTime.isNotEmpty()) selectedTime else "Notify")
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
                            val categories =
                                listOf("Family", "School", "Job", "Personal", "Other")
                            categories.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text(text = category) },
                                    onClick = {
                                        selectedCategory = category
                                        expanded = false
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
                        onDismiss()
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}



