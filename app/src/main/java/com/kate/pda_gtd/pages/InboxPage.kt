package com.kate.pda_gtd.pages

import androidx.activity.ComponentActivity

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class InboxPage:ComponentActivity() {
    @Composable
    fun InboxPageContent() {
        Column {
            ListItem(
                headlineContent = { Text("Congratulate Mom") },
                leadingContent = {
                    val checkedState = remember { mutableStateOf(true) }
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                    )
                }
            )

            ListItem(
                headlineContent = { Text("Do math hw") },
                leadingContent = {
                    val checkedState = remember { mutableStateOf(true) }
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                    )
                }
            )

            ListItem(
                headlineContent = { Text("Plan a vacation") },
                leadingContent = {
                    val checkedState = remember { mutableStateOf(true) }
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                    )
                }
            )
        }

    }
}