package id.co.ppa_github_favorite.core.usecase

import id.co.ppa_github_favorite.core.data.IContentProviderRepository
import javax.inject.Inject

class GetFavorites
@Inject constructor(private val repository: IContentProviderRepository) {

    operator fun invoke() =
        repository.getFavoriteList()

}