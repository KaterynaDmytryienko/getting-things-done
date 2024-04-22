package com.kate.pda_gtd.components

import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.activity.ComponentActivity

import com.kate.pda_gtd.pages.UserProfilePage

class TopBar : ComponentActivity(){
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppBarExample() {
        CenterAlignedTopAppBar(
            title = { Text("", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = { /* Handle navigation icon click */ }) {
                    Icon(Icons.Filled.Menu, "Menu", tint = Color.Gray)
                }
            },
            actions = {
                IconButton(onClick = {
                    val navigate = Intent(this@TopBar, UserProfilePage::class.java)
                    startActivity(navigate)
                }) {
                    Icon(Icons.Filled.AccountCircle, "UserProfile", tint = Color.Black)
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color(0xFFF3EDF7),
                navigationIconContentColor = Color.Gray,
                actionIconContentColor = Color.Gray
            )
        )
    }
}


