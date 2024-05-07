package com.kate.pda_gtd.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kate.pda_gtd.data.CategoryDao
import com.kate.pda_gtd.data.CategoryDao_Impl
import com.kate.pda_gtd.data.CategoryEvent
import com.kate.pda_gtd.data.TaskEvent

class CategoryCreationDialog {
@Composable
fun CategoryDialog(onDismiss: () -> Unit, onEvent: (CategoryEvent)->Unit){
    var categoryName by remember { mutableStateOf("") }
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
                    value = categoryName,
                    onValueChange = {
                        onEvent(CategoryEvent.SetCategoryName(it))
                        categoryName = it
                    },
                    label = { Text("Category name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
                onEvent(CategoryEvent.SaveCategory(categoryName))
                onDismiss()
            }) {
                Text(text = "Create")
            }}

}}}}
