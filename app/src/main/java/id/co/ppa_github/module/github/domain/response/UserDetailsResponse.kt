package id.co.ppa_github.module.github.domain.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetailsResponse(
    @Json(name = "avatar_url")
    val avatarUrl: String? = "",
    @Json(name = "bio")
    val bio: Any? = Any(),
    @Json(name = "blog")
    val blog: String? = "",
    @Json(name = "company")
    val company: String? = "",
    @Json(name = "created_at")
    val createdAt: String? = "",
    @Json(name = "email")
    val email: Any? = Any(),
    @Json(name = "events_url")
    val eventsUrl: String? = "",
    @Json(name = "followers")
    val followers: Int? = 0,
    @Json(name = "followers_url")
    val followersUrl: String? = "",
    @Json(name = "following")
    val following: Int? = 0,
    @Json(name = "following_url")
    val followingUrl: String? = "",
    @Json(name = "gists_url")
    val gistsUrl: String? = "",
    @Json(name = "gravatar_id")
    val gravatarId: String? = "",
    @Json(name = "hireable")
    val hireable: Any? = Any(),
    @Json(name = "html_url")
    val htmlUrl: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "location")
    val location: String? = "",
    @Json(name = "login")
    val login: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "node_id")
    val nodeId: String? = "",
    @Json(name = "organizations_url")
    val organizationsUrl: String? = "",
    @Json(name = "public_gists")
    val publicGists: Int? = 0,
    @Json(name = "public_repos")
    val publicRepos: Int? = 0,
    @Json(name = "received_events_url")
    val receivedEventsUrl: String? = "",
    @Json(name = "repos_url")
    val reposUrl: String? = "",
    @Json(name = "site_admin")
    val siteAdmin: Boolean? = false,
    @Json(name = "starred_url")
    val starredUrl: String? = "",
    @Json(name = "subscriptions_url")
    val subscriptionsUrl: String? = "",
    @Json(name = "twitter_username")
    val twitterUsername: Any? = Any(),
    @Json(name = "type")
    val type: String? = "",
    @Json(name = "updated_at")
    val updatedAt: String? = "",
    @Json(name = "url")
    val url: String? = ""
)