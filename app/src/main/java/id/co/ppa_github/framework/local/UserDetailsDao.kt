package id.co.ppa_github.framework.local

import androidx.room.*
import id.co.ppa_github.core.domain.`object`.UserDetails

@Dao
interface UserDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserDetails(userDetails: UserDetails?)

    @Query("SELECT * FROM user")
    suspend fun getFavorite(): List<UserDetails>?

    @Delete
    suspend fun removeBookmark(userDetails: UserDetails?)

}