package id.co.ppa_github.framework.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import dagger.hilt.android.EntryPointAccessors
import id.co.ppa_github.di.ContentProviderEntryPoint
import id.co.ppa_github.framework.local.UserDetailsDao
import id.co.ppa_github.infrastructure.DatabaseContract.AUTHORITY
import id.co.ppa_github.infrastructure.DatabaseContract.USER_TABLE

class PPAGithubProvider : ContentProvider() {


    companion object {

        private const val GIT = 1

        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        private lateinit var dao: UserDetailsDao

        init {
            // content://id.co.ppa_github/1
            sUriMatcher.addURI(AUTHORITY, USER_TABLE, GIT)

        }

    }

    override fun onCreate(): Boolean {
        val appContext = context?.applicationContext ?: throw IllegalStateException()
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(appContext, ContentProviderEntryPoint::class.java)
        dao = hiltEntryPoint.userDetailsDao()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when (sUriMatcher.match(uri)) {
            GIT -> dao.getProviderFavorite()
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }


}