package id.co.ppa_github.framework.datasource

import android.content.SharedPreferences
import id.co.ppa_github.core.data.ILocalPrefDataSource
import javax.inject.Inject

class LocalPrefDataSource
@Inject constructor(private val pref: SharedPreferences) : ILocalPrefDataSource {

    override fun isDailyReminderExist(): Boolean {
        return pref.getBoolean("isDailyReminderExist", false)
    }

    override fun setDailyReminder() {
        val editor = pref.edit()
        editor.putBoolean("isDailyReminderExist", true)
        editor.apply()
    }

    override fun cancelDailyReminder() {
        val editor = pref.edit()
        editor.putBoolean("isDailyReminderExist", false)
        editor.apply()
    }
}