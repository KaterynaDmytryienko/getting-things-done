package com.kate.pda_gtd.pages
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kate.pda_gtd.components.TaskDialogClass
import com.kate.pda_gtd.data.CategoryViewModel
import com.kate.pda_gtd.data.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*

class CalendarPage : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Calendar() {
        var showDialog by remember { mutableStateOf(false) }
        var dateSelected by remember { mutableStateOf("") }
        val taskViewModel: TaskViewModel = viewModel()
        val categoryViewModel : CategoryViewModel = viewModel()
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
            DatePicker(state = state, modifier = Modifier.padding(16.dp))

            val selectedDate = state.selectedDateMillis?.let {
                val formatter = SimpleDateFormat("d/M/yyyy", Locale.getDefault())
                formatter.format(Date(it))

            } ?: "Date"
            Text(
                "Entered date: $selectedDate",
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp)
            )
            dateSelected = selectedDate
        }
        Column(Modifier.padding(top = 650.dp, bottom = 30.dp, start = 300.dp)) {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Task")
            }
        }

        if (showDialog) {
            TaskDialogClass().TaskDialog(onDismiss = { showDialog = false }, onEvent = { event -> taskViewModel.onEvent(event)}, categoryViewModel = categoryViewModel, pageName = "today", dateSelected)
        }
    }
}


