package com.example.alarmmanager

import android.R.attr
import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var CHANNEL_ID = "channel_id"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notification_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_5 -> showNotification(getNotification("5 seconds delay"), 5000)
            R.id.action_10
            -> showNotification(getNotification("10 seconds delay"), 10000)
            R.id.action_30 -> showNotification(getNotification("30 seconds delay"), 30000)
            else -> println("Number too high")
        }
        return super.onOptionsItemSelected(item)
    }

    fun showNotification(notification: Notification, delay: Int) {
        val notificationIntent = Intent(this, AlarmReceiver::class.java)
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, 1)
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION, notification)
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val futureInMillis = SystemClock.elapsedRealtime() + attr.delay
        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent)
    }

    fun getNotification(content: String): Notification {
        val builder = Notification.Builder(this)
        builder.setContentTitle("Notification")
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        return builder.build()
    }
}