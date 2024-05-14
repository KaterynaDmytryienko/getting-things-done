package com.kate.pda_gtd.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskDao: TaskDao,
    private val categoryDao: CategoryDao
) : ViewModel() {

    private val isSortedByDateadded = MutableStateFlow(true)

    private val tasks =
        isSortedByDateadded.flatMapLatest { sort ->
            if (sort) {
                taskDao.getAllTasks().map { tasks -> tasks.sortedBy { it.dueDate } }
            } else {
                taskDao.getAllTasks()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(TaskState())

    val state = combine(_state,isSortedByDateadded, tasks) {_state, isSortedBuDateAdded, tasks -> _state.copy(
        tasks = tasks
    )}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TaskState())

    val onEvent: (TaskEvent) -> Unit = { event ->
        when (event) {
            is TaskEvent.DeleteTaskById -> viewModelScope.launch {
                taskDao.deleteTask(event.taskId)
            }
            is TaskEvent.SaveTask -> {
                val currentState = _state.value
                if (!currentState.name.value.equals("") && !currentState.category.value.equals("")) {
                    val newTask = Task(
                        name = currentState.name.value,
                        description = currentState.description.value,
                        category = currentState.category.value,
                        dueDate =  currentState.dueDate.value,
                        notificationTime = currentState.notificationTime.value,
                        isCompleted = currentState.isCompleted,
                    )
                    viewModelScope.launch {
                        try {
                            taskDao.insertTask(newTask)
                        } catch (e: Exception) {
                            Log.e("TaskViewModel", "Error inserting task", e)
                        }
                        _state.update { it.copy(name = mutableStateOf(""), description = mutableStateOf(""), category = mutableStateOf(""), dueDate = mutableStateOf(""), notificationTime = mutableStateOf(""), isAddingTask = false) }
                    }
                }
            }
            is TaskEvent.SetTaskName -> _state.update { it.copy(name = mutableStateOf(event.name)) }
            is TaskEvent.SetTaskDescription -> _state.update { it.copy(description = mutableStateOf(event.description)) }
            is TaskEvent.SetCategory -> _state.update { it.copy(category = mutableStateOf(event.category)) }
            is TaskEvent.SetNotificationTime -> _state.update { it.copy(notificationTime  = mutableStateOf(event.time)) }

            TaskEvent.ShowDialog -> TODO()
            is TaskEvent.SetDueDate ->
                _state.update { it.copy(dueDate  = mutableStateOf(event.dueDate)) }

            is TaskEvent.DeleteAllTasksFromCategory ->viewModelScope.launch {
                taskDao.deleteAllTasksFromCategory(event.category)
            }
        }
    }

}
