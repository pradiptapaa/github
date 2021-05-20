package id.co.ppa_github.framework.datasource

import id.co.ppa_github.framework.network.GithubService
import id.co.ppa_github.module.github.data.IGithubDataSource
import id.co.ppa_github.module.github.domain.`object`.Follower
import id.co.ppa_github.module.github.domain.parameter.FollowerParameter
import id.co.ppa_github.module.github.domain.parameter.FollowingParameter
import id.co.ppa_github.module.github.domain.parameter.SearchParameter
import id.co.ppa_github.module.github.domain.parameter.UserDetailsParameter
import id.co.ppa_github.module.github.domain.response.SearchResponse
import id.co.ppa_github.module.github.domain.response.UserDetailsResponse
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