package id.co.ppa_github.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.co.ppa_github.framework.datasource.GithubDataSource
import id.co.ppa_github.framework.network.GithubService
import id.co.ppa_github.module.github.data.IGithubDataSource

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {
    @Provides
    fun provideGithubDataSource(service: GithubService): IGithubDataSource =
        GithubDataSource(service)

}