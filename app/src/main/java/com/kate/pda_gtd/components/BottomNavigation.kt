package com.kate.pda_gtd.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kate.pda_gtd.pages.UserProfilePage
import com.kate.pda_gtd.ui.theme.Purple80

data class NavPage(var name: String, var icon: ImageVector, var route: String)

object Routes {
    val TodayPage = NavPage("Today", Icons.Filled.List, "today")
    val InboxPage = NavPage("Inbox", Icons.Filled.MailOutline, "inbox")
    val TasksPage = NavPage("Tasks", Icons.Filled.Check, "tasks")
    val CalendarPage = NavPage("Calendar", Icons.Filled.DateRange, "calendar")
    val UserProfilePage = NavPage("UserProfile", Icons.Filled.AccountCircle, "userprofile")
    val MainPage = NavPage("MainPage", Icons.Filled.AccountCircle, "mainpage")
    val SettingsPage = NavPage("SettingsPage", Icons.Filled.Settings, "settingspage")
    val AboutPage = NavPage("AboutPage", Icons.Filled.Info, "aboutpage")
    val CategoryPage = NavPage("CategoryPage", Icons.Filled.Info, "categorypage")
    val pages = listOf(TodayPage, InboxPage, TasksPage, CalendarPage)
}



class BottomNavigation {

    @Composable
    fun NavBar(selectedRoute : String = "",
               onChange: (String)->Unit
               ){
        Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(10.dp)){
            for(page in Routes.pages){
              NavBarItem(page, selected = selectedRoute == page.route,
                  modifier = Modifier.clickable {
                      onChange(page.route)
                  }
              )
            }
        }
    }
    @Composable
    fun NavBarItem(page : NavPage, selected : Boolean = false, modifier: Modifier = Modifier) {
       Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(horizontal = 12.dp)){
           Image(

               imageVector = page.icon,
               contentDescription = page.name,
               colorFilter = ColorFilter.tint(
                   if(selected) Purple80 else Color.Black
               ),
               modifier = Modifier
                   .size(24.dp)
           )
           Text(page.name,
               fontSize = 12.sp,
               color = if(selected) Purple80 else Color.Black)
       }
    }
}

