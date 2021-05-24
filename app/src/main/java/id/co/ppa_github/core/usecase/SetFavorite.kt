package id.co.ppa_github.core.usecase

import id.co.ppa_github.core.data.ILocalDbRepository
import id.co.ppa_github.core.domain.`object`.UserDetails
import javax.inject.Inject

class SetFavorite
@Inject constructor(private val repository: ILocalDbRepository) {

    suspend operator fun invoke(parameter: UserDetails?) =
        repository.saveToFavorite(parameter)

}