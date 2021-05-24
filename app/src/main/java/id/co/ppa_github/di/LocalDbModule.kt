package id.co.ppa_github.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.co.ppa_github.framework.local.AppLocalDb
import id.co.ppa_github.framework.local.RConverter
import id.co.ppa_github.framework.local.UserDetailsDao
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class LocalDbModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context, moshi: Moshi): AppLocalDb {
        val converter = RConverter(moshi)
        return Room.databaseBuilder(
            appContext,
            AppLocalDb::class.java,
            "Github"
        ).addTypeConverter(converter).build()
    }

    @Provides
    fun provideChannelDao(appLocalDb: AppLocalDb): UserDetailsDao {
        return appLocalDb.userDetailsDao()
    }

}