package com.kate.pda_gtd.pages

import android.content.Intent
import androidx.activity.ComponentActivity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class TodayPage: ComponentActivity() {
    @Preview
    @Composable
    fun TodayPageContent() {
        val context = LocalContext.current;
        Column(Modifier.padding(top = 70.dp, bottom = 30.dp)) {
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
                headlineContent = { Text("Make a presentation") },
                leadingContent = {
                    val checkedState = remember { mutableStateOf(true) }
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                    )
                }
            )


        }
        Column(Modifier.padding(top = 400.dp, bottom = 30.dp, start = 300.dp)) {
            FloatingActionButton(
                onClick = { val navigate = Intent(context, CreateTaskConclusionPage::class.java)
                    startActivity(navigate)},
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }

    }

}