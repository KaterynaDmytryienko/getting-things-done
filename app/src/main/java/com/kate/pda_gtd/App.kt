package com.kate.pda_gtd

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import com.kate.pda_gtd.components.BottomNavigation
import com.kate.pda_gtd.components.Routes
import com.kate.pda_gtd.components.TopBar
import com.kate.pda_gtd.data.CategoryEvent
import com.kate.pda_gtd.data.CategoryState
import com.kate.pda_gtd.data.TaskEvent
import com.kate.pda_gtd.data.TaskState
import com.kate.pda_gtd.data.TaskViewModel
import com.kate.pda_gtd.pages.AboutPage
import com.kate.pda_gtd.pages.CalendarPage
import com.kate.pda_gtd.pages.CategoryPage
import com.kate.pda_gtd.pages.InboxPage
import com.kate.pda_gtd.pages.MainPage
import com.kate.pda_gtd.pages.SettingsPage
import com.kate.pda_gtd.pages.TaskOverviewPage
import com.kate.pda_gtd.pages.TodayPage
import com.kate.pda_gtd.pages.UserProfilePage
import kotlinx.coroutines.launch

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon : ImageVector
)



@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(state: TaskState, cstate: CategoryState, viewModel: TaskViewModel) {
    val selectedRoute = remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val items = listOf(
        NavigationItem(
            title = "About",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info
        ),
        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )
    )

    Surface(modifier = Modifier.fillMaxSize()) {

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ModalNavigationDrawer(drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                items.forEachIndexed{index, item ->
                    NavigationDrawerItem(label = { Text(text = item.title) }, selected = index == selectedItemIndex, onClick = {
                        selectedItemIndex = index
                        scope.launch { drawerState.close() }
                        if(selectedItemIndex == 0){
                           selectedRoute.value = Routes.AboutPage.route
                        }
                        else if(selectedItemIndex == 1){
                            selectedRoute.value = Routes.SettingsPage.route
                        }
                    },
                        icon = {
                            Icon(imageVector = if(index == selectedItemIndex){
                                item.selectedIcon
                            }else item.unselectedIcon, contentDescription = item.title)
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding))
                }

            }

        }, drawerState = drawerState) {
            Scaffold(
                topBar = {
                    TopBar(onUserProfileClick = {
                        selectedRoute.value = Routes.UserProfilePage.route;
                    },
                        onNavigationIconClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }
                    )
                },
                content = {
                    when {
                       selectedRoute.value ==  Routes.TodayPage.route -> TodayPage().TodayPageContent(state)
                        selectedRoute.value == Routes.InboxPage.route -> InboxPage().InboxPageContent()
                        selectedRoute.value ==  Routes.TasksPage.route -> TaskOverviewPage().TasksPageContent(cstate, state, selectedRoute)
                        selectedRoute.value == Routes.CalendarPage.route -> CalendarPage().Calendar()
                        selectedRoute.value == Routes.UserProfilePage.route -> UserProfilePage().UserProfileContent()
                        selectedRoute.value == Routes.SettingsPage.route -> SettingsPage().SettingsPageContent()
                        selectedRoute.value ==  Routes.AboutPage.route -> AboutPage().AboutPageContent()
                        selectedRoute.value.startsWith(Routes.CategoryPage.route) -> {
                            val categoryName = selectedRoute.value.substringAfterLast("/")
                            CategoryPage().CategoryPageContent(viewModel, categoryName)
                        }
                        else ->
                            MainPage().MainContent()
                    }
                    if (context == UserProfilePage::class.java) {
                        UserProfilePage()
                    }
                },
                bottomBar = {
                    BottomNavigation().NavBar(
                        selectedRoute = selectedRoute.value,
                        onChange = {
                            selectedRoute.value = it
                        }
                    )
                }
            )
        }

    }
}
