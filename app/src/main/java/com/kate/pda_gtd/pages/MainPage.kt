
package com.kate.pda_gtd.pages
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kate.pda_gtd.R


class MainPage : ComponentActivity() {
    @Composable
    fun MainContent() {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CheckMarkGraphic()
                Button(
                    onClick = {
                        val navigate = Intent(this@MainPage, UserProfilePage::class.java)
                        startActivity(navigate)
                    },
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
}

