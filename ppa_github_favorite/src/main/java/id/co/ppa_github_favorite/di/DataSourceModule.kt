package id.co.ppa_github_favorite.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.co.ppa_github_favorite.core.data.IContentProviderDataSource
import id.co.ppa_github_favorite.franework.datasource.ContentProviderDataSource

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    @Provides
    fun provideGithubDataSource(@ApplicationContext context: Context): IContentProviderDataSource =
        ContentProviderDataSource(context)

}