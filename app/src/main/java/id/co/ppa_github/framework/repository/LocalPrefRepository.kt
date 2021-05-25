package id.co.ppa_github.framework.repository

import id.co.ppa_github.core.data.ILocalPrefDataSource
import id.co.ppa_github.core.data.ILocalPrefRepository
import javax.inject.Inject

class LocalPrefRepository
@Inject constructor(private val dataSource: ILocalPrefDataSource) : ILocalPrefRepository {

    override fun setReminder(isExist: Boolean) {
        if (isExist) dataSource.setDailyReminder() else dataSource.cancelDailyReminder()
    }

    override fun isReminderExist(): Boolean {
        return dataSource.isDailyReminderExist()
    }
}