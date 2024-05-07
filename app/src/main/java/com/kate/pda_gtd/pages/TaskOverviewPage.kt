package com.kate.pda_gtd.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kate.pda_gtd.components.CategoryCreationDialog
import com.kate.pda_gtd.data.Category
import com.kate.pda_gtd.data.CategoryEvent
import com.kate.pda_gtd.data.CategoryState
import com.kate.pda_gtd.data.CategoryViewModel
import com.kate.pda_gtd.data.TaskEvent
import com.kate.pda_gtd.data.TaskState
import com.kate.pda_gtd.data.TaskViewModel

class TaskOverviewPage {

    @Composable
    fun TasksPageContent(state: CategoryState, taskState: TaskState, selectedRoute: MutableState<String>) {
        var showCategoryDialog by remember { mutableStateOf(false) }
        val categoryViewModel: CategoryViewModel = viewModel()
        val taskViewModel: TaskViewModel = viewModel()
        var selectedCategory by remember { mutableStateOf<Category?>(null) }

        Column(modifier = Modifier.padding(bottom = 80.dp)) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(state.categories) { category ->
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .clickable {
                                selectedCategory = category
                                selectedRoute.value = "categorypage/${category.name}"
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.FavoriteBorder,
                                contentDescription = category.name,
                                tint = Color(0xFF6200EE),
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = category.name,
                                color = Color.Black,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.weight(1f))
                            IconButton(
                                onClick = {
                                    categoryViewModel.onEvent(CategoryEvent.DeleteCategoryById(category.id))
                                        taskViewModel.onEvent(TaskEvent.DeleteAllTasksFromCategory(category.name))
                                },
                                content = {
                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = "Delete ${category.name}",
                                        tint = Color.Black
                                    )
                                }
                            )
                        }
                    }
                }
            }

            if (showCategoryDialog) {
                CategoryCreationDialog().CategoryDialog(onDismiss = { showCategoryDialog = false }, onEvent = { event -> categoryViewModel.onEvent(event) })
            }
        }
        Column(Modifier.padding(top = 750.dp, bottom = 30.dp, start = 340.dp)) {
            FloatingActionButton(onClick = { showCategoryDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Category")
            }
        }
    }


}
