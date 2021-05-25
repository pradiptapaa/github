package id.co.ppa_github.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.co.ppa_github.BuildConfig

@Module
@InstallIn(ApplicationComponent::class)
object PreferenceModule {

    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE)
    }

}

