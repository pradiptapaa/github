package id.co.ppa_github.ui.setting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import id.co.ppa_github.core.usecase.IsPrefReminderExist
import id.co.ppa_github.core.usecase.SetPrefReminder
import id.co.ppa_github.framework.broadcast.AppBReceiver

class SettingViewModel
@ViewModelInject constructor(
    private val broadcastReceiver: AppBReceiver,
    private val isPrefReminderExist: IsPrefReminderExist,
    private val setPrefReminder: SetPrefReminder
) : ViewModel() {

    fun setReminder(isChecked: Boolean) {
        setPrefReminder(isChecked)
        if (isChecked) {
            broadcastReceiver.setDailyReminder()
        } else {
            broadcastReceiver.cancelDailyReminder()
        }
    }

    fun isReminderExist() = isPrefReminderExist()
}