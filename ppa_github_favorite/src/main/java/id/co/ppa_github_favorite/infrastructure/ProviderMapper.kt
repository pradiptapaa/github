package id.co.ppa_github_favorite.infrastructure

import android.database.Cursor
import id.co.ppa_github_favorite.core.domain.UserDetails
import java.util.*

object ProviderMapper {

    fun mapCursorToList(cursor: Cursor?, converter: ObjectConverter): ArrayList<UserDetails> {
        val list = ArrayList<UserDetails>()

        cursor?.apply {
            while (moveToNext()) {
                val userDetailsJson =
                    getString(getColumnIndexOrThrow(DatabaseContract.USER_DETAILS))
                val userFollowerJson =
                    getString(getColumnIndexOrThrow(DatabaseContract.USER_FOLLOWER))
                val userFollowingJson =
                    getString(getColumnIndexOrThrow(DatabaseContract.USER_FOLLOWING))
                val userDetails = converter.jsonToUserDetails(userDetailsJson)
                val userFollower = converter.jsonToFollowerList(userFollowerJson)
                val userFollowing = converter.jsonToFollowerList(userFollowingJson)
                list += UserDetails(userDetails, userFollower, userFollowing)
            }
        }
        return list
    }
}