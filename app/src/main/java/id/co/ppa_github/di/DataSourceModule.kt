package id.co.ppa_github.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.co.ppa_github.core.data.IGithubDataSource
import id.co.ppa_github.core.data.ILocalDbDataSource
import id.co.ppa_github.core.data.ILocalPrefDataSource
import id.co.ppa_github.framework.datasource.GithubDataSource
import id.co.ppa_github.framework.datasource.LocalDbDataSource
import id.co.ppa_github.framework.datasource.LocalPrefDataSource
import id.co.ppa_github.framework.local.UserDetailsDao
import id.co.ppa_github.framework.network.GithubService

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {
    @Provides
    fun provideGithubDataSource(service: GithubService): IGithubDataSource =
        GithubDataSource(service)

    @Provides
    fun provideLocalDbDataSource(dao: UserDetailsDao): ILocalDbDataSource =
        LocalDbDataSource(dao)

    @Provides
    fun provideLocalPrefDataSource(pref: SharedPreferences): ILocalPrefDataSource =
        LocalPrefDataSource(pref)
}