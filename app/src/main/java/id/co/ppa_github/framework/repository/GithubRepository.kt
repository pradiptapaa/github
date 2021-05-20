package id.co.ppa_github.framework.repository

import id.co.base.ResultOf
import id.co.ppa_github.module.github.data.IGithubDataSource
import id.co.ppa_github.module.github.data.IGithubRepository
import id.co.ppa_github.module.github.domain.`object`.UserDetails
import id.co.ppa_github.module.github.domain.parameter.FollowerParameter
import id.co.ppa_github.module.github.domain.parameter.FollowingParameter
import id.co.ppa_github.module.github.domain.parameter.SearchParameter
import id.co.ppa_github.module.github.domain.parameter.UserDetailsParameter
import id.co.ppa_github.module.github.domain.response.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GithubRepository
@Inject constructor(
    private val dataSource: IGithubDataSource,
) : IGithubRepository {

    override suspend fun searchUsers(parameter: SearchParameter?): SearchResponse? {
        return dataSource.searchUsers(parameter)
    }

    override suspend fun fetchUserDetails(
        parameter: UserDetailsParameter?
    ): Flow<ResultOf<UserDetails?>> {
        return flow {
            emit(ResultOf.Loading())
            val userDetails = dataSource
                .fetchUserDetails(parameter)
            val userFollower = dataSource
                .fetchUserFollower(FollowerParameter(parameter?.username))
            val userFollowing =
                dataSource.fetchUserFollowing(FollowingParameter(parameter?.username))
            emit(ResultOf.Success(UserDetails(userDetails, userFollower, userFollowing)))
        }.catch { e ->
            emit(ResultOf.Failure(e))
        }
    }


}