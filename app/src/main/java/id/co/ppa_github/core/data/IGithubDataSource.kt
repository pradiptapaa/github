package id.co.ppa_github.core.data

import id.co.ppa_github.core.domain.`object`.Follower
import id.co.ppa_github.core.domain.parameter.FollowerParameter
import id.co.ppa_github.core.domain.parameter.FollowingParameter
import id.co.ppa_github.core.domain.parameter.SearchParameter
import id.co.ppa_github.core.domain.parameter.UserDetailsParameter
import id.co.ppa_github.core.domain.response.SearchResponse
import id.co.ppa_github.core.domain.response.UserDetailsResponse

interface IGithubDataSource {

    suspend fun searchUsers(parameter: SearchParameter?): SearchResponse?

    suspend fun fetchUserDetails(parameter: UserDetailsParameter?): UserDetailsResponse?

    suspend fun fetchUserFollower(parameter: FollowerParameter?): List<Follower>

    suspend fun fetchUserFollowing(parameter: FollowingParameter?): List<Follower>
}