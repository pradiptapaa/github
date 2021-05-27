package id.co.ppa_github.core.domain.`object`

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.co.ppa_github.core.domain.response.UserDetailsResponse
import id.co.ppa_github.infrastructure.DatabaseContract.USER_DETAILS
import id.co.ppa_github.infrastructure.DatabaseContract.USER_FOLLOWER
import id.co.ppa_github.infrastructure.DatabaseContract.USER_FOLLOWING
import id.co.ppa_github.infrastructure.DatabaseContract.USER_TABLE
import kotlinx.parcelize.Parcelize

@Entity(tableName = USER_TABLE)
@Parcelize
data class UserDetails(
    @ColumnInfo(name = USER_DETAILS)
    val userDetailsResponse: UserDetailsResponse? = UserDetailsResponse(),
    @ColumnInfo(name = USER_FOLLOWER)
    val userFollowerResponse: List<Follower>? = listOf(),
    @ColumnInfo(name = USER_FOLLOWING)
    val userFollowingResponse: List<Follower>? = listOf(),
    @PrimaryKey
    val login: String = "",
) : Parcelable