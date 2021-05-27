package id.co.ppa_github_favorite.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.co.ppa_github_favorite.core.data.IContentProviderDataSource
import id.co.ppa_github_favorite.core.data.IContentProviderRepository
import id.co.ppa_github_favorite.franework.repository.ContentProviderRepository
import id.co.ppa_github_favorite.infrastructure.ObjectConverter
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideGithubRepository(
        dataSource: IContentProviderDataSource,
        objectConverter: ObjectConverter
    ): IContentProviderRepository =
        ContentProviderRepository(dataSource, objectConverter)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}