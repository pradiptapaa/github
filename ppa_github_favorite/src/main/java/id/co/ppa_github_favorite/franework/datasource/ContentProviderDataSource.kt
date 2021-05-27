package id.co.ppa_github_favorite.franework.datasource

import android.content.Context
import android.database.Cursor
import id.co.ppa_github_favorite.core.data.IContentProviderDataSource
import id.co.ppa_github_favorite.infrastructure.DatabaseContract.CONTENT_URI

class ContentProviderDataSource(private val context: Context) : IContentProviderDataSource {

    override fun fetchFavoriteList(): Cursor? {
        return context.contentResolver
            .query(CONTENT_URI, null, null, null, null)
    }
}