package id.co.ppa_github.framework.network

import id.co.ppa_github.core.domain.`object`.Follower
import id.co.ppa_github.core.domain.response.SearchResponse
import id.co.ppa_github.core.domain.response.UserDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") username: String?
    ): SearchResponse?

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") username: String?,
        @Query("page") page: Int?
    ): SearchResponse?

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String?
    ): UserDetailsResponse?

    @GET("users/{username}/followers")
    suspend fun getFollower(
        @Path("username") username: String?
    ): List<Follower>

    @GET("users/{username}/followers")
    suspend fun getFollower(
        @Path("username") username: String?,
        @Query("page") page: Int?
    ): List<Follower>

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String?
    ): List<Follower>


    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String?,
        @Query("page") page: Int?
    ): List<Follower>
}