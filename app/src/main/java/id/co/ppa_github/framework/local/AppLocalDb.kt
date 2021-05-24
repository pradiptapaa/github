package id.co.ppa_github.framework.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.co.ppa_github.core.domain.`object`.UserDetails

@Database(entities = [UserDetails::class], version = 1, exportSchema = false)
@TypeConverters(RConverter::class)
abstract class AppLocalDb : RoomDatabase() {
    abstract fun userDetailsDao(): UserDetailsDao
}