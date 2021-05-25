package id.co.ppa_github.core.usecase

import id.co.ppa_github.core.data.ILocalPrefRepository
import javax.inject.Inject

class IsPrefReminderExist
@Inject constructor(private val repository: ILocalPrefRepository) {

    operator fun invoke() =
        repository.isReminderExist()

}