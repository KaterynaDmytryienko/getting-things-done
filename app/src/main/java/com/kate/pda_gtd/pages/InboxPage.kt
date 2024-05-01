package com.kate.pda_gtd.pages

import androidx.activity.ComponentActivity

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


class InboxPage:ComponentActivity() {
    @Composable
    fun InboxPageContent() {
        Column {
            ListItem(
                headlineContent = { Text("Congratulate Mom") },
                trailingContent = {
                    AssistChip(
                        onClick = {  },
                        label = { Text("3 days") },
                    )
                }
            )

            ListItem(
                headlineContent = { Text("Do math hw") },
                trailingContent = {
                    AssistChip(
                        onClick = {  },
                        label = { Text("3 days") },
                    )
                }
            )

            ListItem(
                headlineContent = { Text("Plan a vacation") },
                trailingContent = {
                    AssistChip(
                        onClick = {  },
                        label = { Text("3 days") },
                    )
                }
            )
        }

    }
}