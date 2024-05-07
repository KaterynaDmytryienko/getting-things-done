package com.kate.pda_gtd.data

import androidx.compose.runtime.State

data class ViewStates(
    val taskState: State<TaskState>,
    val categoryState: State<CategoryState>
)