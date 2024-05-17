package com.kate.pda_gtd.pages


import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import com.kate.pda_gtd.components.NotificationService

class SettingsPage {

    @RequiresApi(Build.VERSION_CODES.O)
    fun openNotificationSettings(context: Context) {
        val intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            putExtra(Settings.EXTRA_CHANNEL_ID, NotificationService.NOTIFICATION_ID)
        }
        context.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun SettingsPageContent() {
        val context = LocalContext.current
        val prefs = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val isNotificationsEnabled = remember {
            mutableStateOf(prefs.getBoolean("notifications_sound", true))
        }
        Surface {
            Column(modifier = Modifier.padding(top = 80.dp, start = 20.dp, end = 20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Customize notifications",
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = isNotificationsEnabled.value,
                        onCheckedChange = { isEnabled ->
                            isNotificationsEnabled.value = isEnabled
                            prefs.edit().putBoolean("notifications_sound", isEnabled).apply()
                            openNotificationSettings(context)
                        }
                    )
                }
            }
        }
    }
}
