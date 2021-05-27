package id.co.ppa_github_favorite.core.data

import android.database.Cursor

interface IContentProviderDataSource {

    fun fetchFavoriteList(): Cursor?

}