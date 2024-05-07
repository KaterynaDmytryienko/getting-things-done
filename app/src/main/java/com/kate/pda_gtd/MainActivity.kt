package com.kate.pda_gtd

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kate.pda_gtd.data.CategoryViewModel
import com.kate.pda_gtd.data.GTD_Database
import com.kate.pda_gtd.data.TaskViewModel
import com.kate.pda_gtd.ui.theme.PDA_GTDTheme

class MainActivity : ComponentActivity() {
    private val db by lazy { GTD_Database.getDatabase(application) }

    private val taskViewModel by viewModels<TaskViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return TaskViewModel(db.taskDao(), db.categoryDao()) as T
                } else {
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }

    private val categoryViewModel by viewModels<CategoryViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return CategoryViewModel(db.categoryDao(), db.taskDao()) as T
                } else {
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val taskState = taskViewModel.state.collectAsState()
            val categoryState = categoryViewModel.state.collectAsState()
            PDA_GTDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(taskState.value, categoryState.value, taskViewModel)
                }
            }
        }
    }
}