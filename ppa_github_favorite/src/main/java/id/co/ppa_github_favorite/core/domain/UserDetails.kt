package id.co.ppa_github_favorite.core.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetails(
    val userDetailsResponse: UserDetailsResponse? = UserDetailsResponse(),
    val userFollowerResponse: List<Follower>? = listOf(),
    val userFollowingResponse: List<Follower>? = listOf(),
    val login: String = "",
) : Parcelable