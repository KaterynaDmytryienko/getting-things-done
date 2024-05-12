package com.kate.pda_gtd.components

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val service = NotificationService(context)
        val taskName = intent?.getStringExtra("TASK_NAME")
        service.showNotification(taskName, "Your task $taskName is due now.")
    }
}