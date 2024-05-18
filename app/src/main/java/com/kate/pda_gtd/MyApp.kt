package com.kate.pda_gtd

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.kate.pda_gtd.components.NotificationService
import android.provider.Settings

class MyApp :Application(){
    companion object {
        const val PREFS_NAME = "app_settings"
        const val SOUND_KEY = "notifications_sound"
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val enableSound = prefs.getBoolean(SOUND_KEY, true)

        val channel = NotificationChannel(
            NotificationService.NOTIFICATION_ID,
            "GTD",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Notification channel for reminders"
            setSound(
                if (enableSound) Settings.System.DEFAULT_NOTIFICATION_URI else null,
                Notification.AUDIO_ATTRIBUTES_DEFAULT
            )
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (notificationManager.getNotificationChannel(NotificationService.NOTIFICATION_ID) != null) {
            notificationManager.deleteNotificationChannel(NotificationService.NOTIFICATION_ID)
        }
        notificationManager.createNotificationChannel(channel)
    }
}