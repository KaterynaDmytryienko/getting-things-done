package com.kate.pda_gtd.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TaskState(
    val tasks : List<Task> = emptyList(),
    val name: MutableState<String> = mutableStateOf(""),
    val category: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf(""),
    val notificationTime: MutableState<String> = mutableStateOf(""),
    val dueDate: MutableState<String> = mutableStateOf(""),
    val isAddingTask : Boolean = false,
    val isCompleted : Boolean = false

)