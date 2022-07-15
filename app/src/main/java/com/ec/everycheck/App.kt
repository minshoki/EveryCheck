package com.ec.everycheck

import android.app.Application
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannelCompat.Builder(
            "my_default_channel_id",
            NotificationManagerCompat.IMPORTANCE_DEFAULT
        )
            .setName("알림")
            .setShowBadge(true)
            .setVibrationPattern(longArrayOf(0))
            .setVibrationEnabled(true)
            .build()

        NotificationManagerCompat.from(this).createNotificationChannel(channel)

    }

}