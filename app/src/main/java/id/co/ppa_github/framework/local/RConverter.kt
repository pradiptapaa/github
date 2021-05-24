package id.co.ppa_github.framework.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import id.co.ppa_github.core.domain.`object`.Follower
import id.co.ppa_github.core.domain.response.UserDetailsResponse

@ProvidedTypeConverter
class RConverter(private val moshi: Moshi) {

    @TypeConverter
    fun userDetailsToJson(userDetails: UserDetailsResponse?): String? {
        val adapter = moshi.adapter(UserDetailsResponse::class.java)
        return adapter.toJson(userDetails)
    }

    @TypeConverter
    fun jsonToUserDetails(jsonString: String?): UserDetailsResponse? {
        val adapter = moshi.adapter(UserDetailsResponse::class.java)
        return adapter.fromJson(jsonString!!)
    }

    @TypeConverter
    fun followerListToJson(listFollower: List<Follower>?): String? {
        val type = Types.newParameterizedType(List::class.java, Follower::class.java)
        val adapter = moshi.adapter<List<Follower>>(type)
        return adapter.toJson(listFollower)
    }

    @TypeConverter
    fun jsonToFollowerList(jsonString: String?): List<Follower>? {
        val type = Types.newParameterizedType(List::class.java, Follower::class.java)
        val adapter = moshi.adapter<List<Follower>>(type)
        return adapter.fromJson(jsonString!!)
    }


}