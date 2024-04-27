package com.kate.pda_gtd.components
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onUserProfileClick: () -> Unit, onNavigationIconClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text("", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(Icons.Filled.Menu, "Menu", tint = Color.Gray)
            }
        },
        actions = {
            IconButton(onClick = onUserProfileClick) {
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

