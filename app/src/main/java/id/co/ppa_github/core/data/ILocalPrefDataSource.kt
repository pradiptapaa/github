package id.co.ppa_github.core.data

interface ILocalPrefDataSource {

    fun isDailyReminderExist(): Boolean

    fun setDailyReminder()

    fun cancelDailyReminder()
}