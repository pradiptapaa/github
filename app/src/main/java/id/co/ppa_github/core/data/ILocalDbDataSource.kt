package id.co.ppa_github.core.data

import id.co.ppa_github.core.domain.`object`.UserDetails

interface ILocalDbDataSource {

    suspend fun saveToFavorite(userDetails: UserDetails?)

    suspend fun fetchFavoriteList(): List<UserDetails>?

    suspend fun removeUserDetails(userDetails: UserDetails?)

}