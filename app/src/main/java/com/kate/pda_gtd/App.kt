package com.kate.pda_gtd

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.kate.pda_gtd.components.BottomNavigation
import com.kate.pda_gtd.components.Routes
import com.kate.pda_gtd.components.TopBar
import com.kate.pda_gtd.pages.CalendarPage
import com.kate.pda_gtd.pages.InboxPage
import com.kate.pda_gtd.pages.MainPage
import com.kate.pda_gtd.pages.TaskOverviewPage
import com.kate.pda_gtd.pages.TodayPage



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
    fun App(){
        val selectedRoute = remember {
            mutableStateOf("")
        }
        Scaffold (
            topBar = {
                TopBar().AppBarExample()
    },
            content = {
                when(selectedRoute.value){
                    Routes.TodayPage.route -> TodayPage().TodayPageContent()
                    Routes.InboxPage.route -> InboxPage().InboxPageContent()
                    Routes.TasksPage.route -> TaskOverviewPage()
                    Routes.CalendarPage.route -> CalendarPage()
                    else -> MainPage().MainContent()
                }
                      },
            bottomBar = { BottomNavigation().NavBar (
                selectedRoute = selectedRoute.value,
                onChange = {
                      selectedRoute.value = it
                }
            )
    })
}


