package id.co.ppa_github.framework.datasource

import id.co.ppa_github.core.data.IGithubDataSource
import id.co.ppa_github.core.domain.`object`.Follower
import id.co.ppa_github.core.domain.parameter.FollowerParameter
import id.co.ppa_github.core.domain.parameter.FollowingParameter
import id.co.ppa_github.core.domain.parameter.SearchParameter
import id.co.ppa_github.core.domain.parameter.UserDetailsParameter
import id.co.ppa_github.core.domain.response.SearchResponse
import id.co.ppa_github.core.domain.response.UserDetailsResponse
import id.co.ppa_github.framework.network.GithubService
import javax.inject.Inject

class GithubDataSource
@Inject constructor(private val service: GithubService) : IGithubDataSource {

    override suspend fun searchUsers(parameter: SearchParameter?): SearchResponse? {
        return service.getUsers(parameter?.username, parameter?.page)
    }

    override suspend fun fetchUserDetails(parameter: UserDetailsParameter?): UserDetailsResponse? {
        return service.getUser(parameter?.username)
    }

    override suspend fun fetchUserFollower(parameter: FollowerParameter?): List<Follower> {
        return service.getFollower(parameter?.username)
    }

    override suspend fun fetchUserFollowing(parameter: FollowingParameter?): List<Follower> {
        return service.getFollowing(parameter?.username)
    }
}