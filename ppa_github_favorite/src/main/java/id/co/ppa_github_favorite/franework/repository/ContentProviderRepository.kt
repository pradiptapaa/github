package id.co.ppa_github_favorite.franework.repository

import id.co.ppa_github_favorite.core.data.IContentProviderDataSource
import id.co.ppa_github_favorite.core.data.IContentProviderRepository
import id.co.ppa_github_favorite.core.domain.UserDetails
import id.co.ppa_github_favorite.infrastructure.ObjectConverter
import id.co.ppa_github_favorite.infrastructure.ProviderMapper.mapCursorToList

class ContentProviderRepository(
    private val dataSource: IContentProviderDataSource,
    private val objectConverter: ObjectConverter
) : IContentProviderRepository {

    override fun getFavoriteList(): List<UserDetails> {
        return mapCursorToList(dataSource.fetchFavoriteList(), objectConverter)
    }
}