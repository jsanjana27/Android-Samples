package com.example.alarmmanager

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
Created by sanjana on 30/4/20
 */


open class AlarmReceiver : BroadcastReceiver() {
    companion object {
        var NOTIFICATION_ID = "notification-id"
        var NOTIFICATION = "notification"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager: NotificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: Notification = intent!!.getParcelableExtra(NOTIFICATION)
        val id = intent!!.getIntExtra(NOTIFICATION_ID, 0)
        notificationManager.notify(id, notification)
    }
}