package id.co.ppa_github_favorite.infrastructure

import android.net.Uri

object DatabaseContract {

    const val USER_TABLE = "user"
    const val USER_DETAILS = "user_details"
    const val USER_FOLLOWER = "user_follower"
    const val USER_FOLLOWING = "user_following"

    const val AUTHORITY = "id.co.ppa_github"
    const val SCHEME = "content"

    val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(USER_TABLE)
        .build()
}