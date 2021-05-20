package id.co.ppa_github.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.co.ppa_github.core.data.IGithubDataSource
import id.co.ppa_github.core.data.IGithubRepository
import id.co.ppa_github.framework.repository.GithubRepository

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideGithubRepository(
        dataSource: IGithubDataSource
    ): IGithubRepository =
        GithubRepository(dataSource)
}