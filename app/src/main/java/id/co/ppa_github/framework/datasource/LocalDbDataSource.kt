package id.co.ppa_github.framework.datasource

import id.co.ppa_github.core.data.ILocalDbDataSource
import id.co.ppa_github.core.domain.`object`.UserDetails
import id.co.ppa_github.framework.local.UserDetailsDao
import javax.inject.Inject

class LocalDbDataSource
@Inject constructor(private val dao: UserDetailsDao) : ILocalDbDataSource {

    override suspend fun saveToFavorite(userDetails: UserDetails?) {
        dao.addUserDetails(userDetails)
    }

    override suspend fun fetchFavoriteList(): List<UserDetails>? {
        return dao.getFavorite()
    }

    override suspend fun removeUserDetails(userDetails: UserDetails?) {
        return dao.removeBookmark(userDetails)
    }
}