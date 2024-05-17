package com.kate.pda_gtd.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryDao: CategoryDao,
    private val taskDao : TaskDao
) : ViewModel() {

    private val isSortedByDateadded = MutableStateFlow(true)

     val categories =
        isSortedByDateadded.flatMapLatest { sort ->
            if (sort) {
                categoryDao.getAllCategories().map { categories -> categories.sortedBy { it.name } }
            } else {
                categoryDao.getAllCategories()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(CategoryState())

    val state = combine(_state,isSortedByDateadded, categories) {_state, isSortedBuDateAdded, categories -> _state.copy(
        categories = categories
    )}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CategoryState())

    val onEvent: (CategoryEvent) -> Unit = { event ->
        when (event) {
            is CategoryEvent.SetCategoryName ->
                _state.update { it.copy(name = mutableStateOf(event.name)) }
            is CategoryEvent.SaveCategory -> {
                val currentState = _state.value
                if (!currentState.name.value.equals("")) {
                    val newCategory = Category(
                        name = currentState.name.value
                    )
                    viewModelScope.launch {
                        categoryDao.insertCategory(newCategory)
                        _state.update { it.copy(name = mutableStateOf(""))}
                    }
                }
            }
            is CategoryEvent.DeleteCategoryById -> {
                viewModelScope.launch {
                    categoryDao.deleteCategory(event.categoryId)
                }
            }

        }
    }

}
