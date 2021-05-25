package id.co.ppa_github.framework.broadcast

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import id.co.ppa_github.BuildConfig
import id.co.ppa_github.R
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AppBReceiver @Inject constructor() : BroadcastReceiver() {

    @Inject
    @ApplicationContext
    lateinit var context: Context

    @Inject
    lateinit var alarmManager: AlarmManager

    @Inject
    lateinit var notifManager: NotificationManager

    @Inject
    lateinit var notifBuilder: NotificationCompat.Builder

    companion object {
        const val KEY_TITLE = "key_title"
        const val KEY_MESSAGE = "key_message"

        const val ID_DAILY = 100
    }

    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra(KEY_TITLE)
        val message = intent.getStringExtra(KEY_MESSAGE)

        if (!message.isNullOrEmpty()) {
            showNotification(title, message)
        }
    }

    fun setDailyReminder() {
        val intent = Intent(context, AppBReceiver::class.java).apply {
            this.putExtra(KEY_TITLE, context.getString(R.string.notif_reminder_title))
            this.putExtra(KEY_MESSAGE, context.getString(R.string.notif_reminder_message))
        }
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 9)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_DAILY, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

    }

    fun cancelDailyReminder() {
        val intent = Intent(context, AppBReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_DAILY, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }

    private fun showNotification(title: String?, message: String?) {
        val channelId = "channel_id1"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, BuildConfig.CHANNEL_NOTIF,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            notifManager.createNotificationChannel(channel)
            notifBuilder.setChannelId(channelId)
        }
        val notification = notifBuilder.setContentTitle(title).setContentText(message).build()
        notifManager.notify(ID_DAILY, notification)
    }
}