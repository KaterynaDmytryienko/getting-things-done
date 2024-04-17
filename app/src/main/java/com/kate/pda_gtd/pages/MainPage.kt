
package com.kate.pda_gtd.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.kate.pda_gtd.R
import com.kate.pda_gtd.components.BottomNavigation
import com.kate.pda_gtd.components.TopBar

class MainPage : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainPageContent()
        }
    }

    @Composable
    fun MainContent() {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CheckMarkGraphic()
                Button(
                    onClick = { /* TODO: Handle click here */ },
                    modifier = Modifier
                        .height(58.dp)
                        .width(204.dp),
                    shape = RoundedCornerShape(30.dp),
                ) {
                    Text("My profile", color = Color.White)
                }
        }

    }

    @Composable
    fun CheckMarkGraphic() {
        Icon(
            painterResource(id = R.drawable.ic_logo),
            contentDescription = "App logo",
            modifier = Modifier
                .size(200.dp)
        )
    }

    @Composable
    fun MainPageContent() {
        val navController = rememberNavController()
        MaterialTheme {
            Scaffold(
                topBar = { TopBar().AppBarExample(navController) },
                content = { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainContent()
                    }
                },
                bottomBar = { BottomNavigation().BottomAppBarExample() }
            )
        }
    }
}

