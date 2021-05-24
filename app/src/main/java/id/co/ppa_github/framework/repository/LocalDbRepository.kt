package id.co.ppa_github.framework.repository

import id.co.base.ResultOf
import id.co.ppa_github.core.data.ILocalDbDataSource
import id.co.ppa_github.core.data.ILocalDbRepository
import id.co.ppa_github.core.domain.`object`.UserDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LocalDbRepository(private val dataSource: ILocalDbDataSource) :
    ILocalDbRepository {

    override suspend fun saveToFavorite(userDetails: UserDetails?) {
        val userDetail = UserDetails(
            userDetailsResponse = userDetails?.userDetailsResponse,
            userFollowerResponse = userDetails?.userFollowerResponse,
            userFollowingResponse = userDetails?.userFollowingResponse,
            login = userDetails?.userDetailsResponse?.login!!
        )
        dataSource.saveToFavorite(userDetail)
    }

    override suspend fun fetchFavoriteList(): Flow<ResultOf<List<UserDetails>?>> {
        return flow {
            emit(ResultOf.Loading())
            emit(ResultOf.Success(dataSource.fetchFavoriteList()))
        }.catch { e ->
            emit(ResultOf.Failure(e))
        }
    }

    override suspend fun removeUserDetails(userDetails: UserDetails?) {
        val userDetail = UserDetails(
            userDetailsResponse = userDetails?.userDetailsResponse,
            userFollowerResponse = userDetails?.userFollowerResponse,
            userFollowingResponse = userDetails?.userFollowingResponse,
            login = userDetails?.userDetailsResponse?.login!!
        )
        dataSource.removeUserDetails(userDetail)
    }
}