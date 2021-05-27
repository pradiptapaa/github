package id.co.ppa_github_favorite.infrastructure

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import id.co.ppa_github_favorite.core.domain.Follower
import id.co.ppa_github_favorite.core.domain.UserDetailsResponse
import javax.inject.Inject

class ObjectConverter @Inject constructor(private val moshi: Moshi) {

    fun userDetailsToJson(userDetails: UserDetailsResponse?): String? {
        val adapter = moshi.adapter(UserDetailsResponse::class.java)
        return adapter.toJson(userDetails)
    }

    fun jsonToUserDetails(jsonString: String?): UserDetailsResponse? {
        val adapter = moshi.adapter(UserDetailsResponse::class.java)
        return adapter.fromJson(jsonString!!)
    }

    fun followerListToJson(listFollower: List<Follower>?): String? {
        val type = Types.newParameterizedType(List::class.java, Follower::class.java)
        val adapter = moshi.adapter<List<Follower>>(type)
        return adapter.toJson(listFollower)
    }

    fun jsonToFollowerList(jsonString: String?): List<Follower>? {
        val type = Types.newParameterizedType(List::class.java, Follower::class.java)
        val adapter = moshi.adapter<List<Follower>>(type)
        return adapter.fromJson(jsonString!!)
    }


}