package id.co.ppa_github.core.domain.`object`

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.co.ppa_github.core.domain.response.UserDetailsResponse
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user")
@Parcelize
data class UserDetails(
    @ColumnInfo(name = "user_details")
    val userDetailsResponse: UserDetailsResponse? = UserDetailsResponse(),
    @ColumnInfo(name = "user_follower")
    val userFollowerResponse: List<Follower>? = listOf(),
    @ColumnInfo(name = "user_following")
    val userFollowingResponse: List<Follower>? = listOf(),
    @PrimaryKey
    val login: String = "",
) : Parcelable