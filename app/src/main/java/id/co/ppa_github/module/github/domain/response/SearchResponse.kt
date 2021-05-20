package id.co.ppa_github.module.github.domain.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import id.co.ppa_github.module.github.domain.`object`.User

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean? = false,
    @Json(name = "items")
    val users: List<User>? = listOf(),
    @Json(name = "total_count")
    val totalCount: Int? = 0
)