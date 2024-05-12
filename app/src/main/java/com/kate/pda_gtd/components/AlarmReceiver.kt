package com.kate.pda_gtd.components

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val taskName = intent.getStringExtra("TASK_NAME")
        NotificationService(context).showNotification(taskName, "Your task $taskName is due now.")
    }
}