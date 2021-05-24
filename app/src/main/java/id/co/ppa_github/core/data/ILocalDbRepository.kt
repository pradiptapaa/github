package id.co.ppa_github.core.data

import id.co.base.ResultOf
import id.co.ppa_github.core.domain.`object`.UserDetails
import kotlinx.coroutines.flow.Flow

interface ILocalDbRepository {

    suspend fun saveToFavorite(userDetails: UserDetails?)

    suspend fun fetchFavoriteList(): Flow<ResultOf<List<UserDetails>?>>

    suspend fun removeUserDetails(userDetails: UserDetails?)

}