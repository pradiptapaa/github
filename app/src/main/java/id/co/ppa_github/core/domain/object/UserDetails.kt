package id.co.ppa_github.core.domain.`object`

import id.co.ppa_github.core.domain.response.UserDetailsResponse

data class UserDetails(
    val userDetailsResponse: UserDetailsResponse? = UserDetailsResponse(),
    val userFollowerResponse: List<Follower>? = listOf(),
    val userFollowingResponse: List<Follower>? = listOf()
)