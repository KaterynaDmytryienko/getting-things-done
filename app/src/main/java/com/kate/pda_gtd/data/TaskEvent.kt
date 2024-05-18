package com.kate.pda_gtd.data

sealed interface TaskEvent {
    data class SaveTask(
        val name:String,
        val description:String,
        val category : String,
        val notificationTime : String,
        val selectedDate : String,
        val isCompleted : Boolean,
    ): TaskEvent
    data class SetTaskName(val name: String): TaskEvent
    data class SetTaskDescription(val description: String): TaskEvent
    data class SetNotificationTime(val time : String): TaskEvent
    data class SetCategory(val category: String): TaskEvent
    data class SetDueDate(val dueDate: String): TaskEvent
    data class DeleteTaskById(val taskId: Long): TaskEvent
    data class DeleteAllTasksFromCategory(val category: String) : TaskEvent

}