package com.kate.pda_gtd.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class CategoryState (
    val categories: List<Category> = emptyList(),
    val name: MutableState<String> = mutableStateOf(""),
)