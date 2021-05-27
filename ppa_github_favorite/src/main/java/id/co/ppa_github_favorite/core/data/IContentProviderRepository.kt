package id.co.ppa_github_favorite.core.data

import id.co.ppa_github_favorite.core.domain.UserDetails

interface IContentProviderRepository {

    fun getFavoriteList(): List<UserDetails>

}