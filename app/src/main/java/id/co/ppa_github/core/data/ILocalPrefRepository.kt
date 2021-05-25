package id.co.ppa_github.core.data

interface ILocalPrefRepository {

    fun setReminder(isExist: Boolean)

    fun isReminderExist(): Boolean
}