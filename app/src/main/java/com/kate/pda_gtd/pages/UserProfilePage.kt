package com.kate.pda_gtd.pages

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import coil.compose.rememberAsyncImagePainter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.kate.pda_gtd.R
import com.kate.pda_gtd.components.CameraViewContent
import com.kate.pda_gtd.data.UserEvent
import com.kate.pda_gtd.data.UserState
import com.kate.pda_gtd.data.UserViewModel
import java.io.File
import java.util.concurrent.Executors

class UserProfilePage : ComponentActivity() {
    @Composable
    fun UserProfileContent(state: UserState, viewModel: UserViewModel) {
        val context = LocalContext.current
        var photoUri: Uri? by remember { mutableStateOf(Uri.parse(state.photo)) }
        var showCamera by remember { mutableStateOf(false) }


        fun handleImageCapture(uri: Uri) {
            photoUri = uri
            showCamera = false
        }

        fun getOutputDirectory(): File {
            val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
                File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() }
            }
            return if ((mediaDir != null) && mediaDir.exists()) mediaDir else context.filesDir
        }

        var hasCameraPermission by remember {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            )
        }

        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                hasCameraPermission = isGranted
            }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            if (showCamera) {
                CameraViewContent(
                    outputDirectory = getOutputDirectory(),
                    executor = Executors.newSingleThreadExecutor(),
                    onImageCaptured = ::handleImageCapture,
                    onError = {},
                )
            }

            if (state.photo != "") {

                Image(
                    painter = rememberAsyncImagePainter(photoUri),
                    contentScale = ContentScale.Crop,
                    contentDescription = "User Photo",
                    modifier = Modifier
                        .height(240.dp)
                        .width(240.dp)
                        .background(color = Color.Transparent, shape = CircleShape)
                        .clip(CircleShape)
                )
            } else {
                UserCheckMarkGraphic()
            }
            viewModel.onEvent(UserEvent.SetPhoto(photoUri.toString()))
            Button(
                onClick = {
                    if (!hasCameraPermission) {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    } else {
                        showCamera = true
                    }
                },
                modifier = Modifier
                    .height(58.dp)
                    .width(204.dp),
                shape = RoundedCornerShape(30.dp),

                ) {
                Text("Change profile picture", color = Color.White)
            }
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


