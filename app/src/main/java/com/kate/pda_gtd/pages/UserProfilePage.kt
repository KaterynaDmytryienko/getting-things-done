package com.kate.pda_gtd.pages
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
class UserProfilePage : ComponentActivity() {

    @Composable
    fun UserProfileContent() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            UserCheckMarkGraphic()
            Button(
                onClick = { /* Handle different navigation or actions */ },
                modifier = Modifier
                    .height(58.dp)
                    .width(204.dp),
                shape = RoundedCornerShape(30.dp),

            ) {
                Text("Change profile picture", color = Color.White)
            }
        }
    }

    @Composable
    fun UserCheckMarkGraphic() {
        Icon(
            painterResource(id = R.drawable.ic_profile_picture),
            contentDescription = "Profile logo",
            modifier = Modifier.size(200.dp)
        )
    }
}
