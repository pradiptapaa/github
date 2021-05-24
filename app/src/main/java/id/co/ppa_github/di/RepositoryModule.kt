package id.co.ppa_github.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.co.ppa_github.core.data.IGithubDataSource
import id.co.ppa_github.core.data.IGithubRepository
import id.co.ppa_github.core.data.ILocalDbDataSource
import id.co.ppa_github.core.data.ILocalDbRepository
import id.co.ppa_github.framework.repository.GithubRepository
import id.co.ppa_github.framework.repository.LocalDbRepository

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideGithubRepository(
        dataSource: IGithubDataSource
    ): IGithubRepository =
        GithubRepository(dataSource)

    @Provides
    fun provideLocalDbRepository(
        dataSource: ILocalDbDataSource
    ): ILocalDbRepository =
        LocalDbRepository(dataSource)
}