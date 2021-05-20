package id.co.ppa_github.module.github.domain.`object`

import id.co.ppa_github.module.github.domain.response.UserDetailsResponse

data class UserDetails(
    val userDetailsResponse: UserDetailsResponse? = UserDetailsResponse(),
    val userFollowerResponse: List<Follower>? = listOf(),
    val userFollowingResponse: List<Follower>? = listOf()
)