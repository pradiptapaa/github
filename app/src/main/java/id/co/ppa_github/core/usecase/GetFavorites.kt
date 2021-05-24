package id.co.ppa_github.core.usecase

import id.co.ppa_github.core.data.ILocalDbRepository
import javax.inject.Inject

class GetFavorites
@Inject constructor(private val repository: ILocalDbRepository) {

    suspend operator fun invoke() =
        repository.fetchFavoriteList()

}