package id.co.ppa_github.core.data

import id.co.base.ResultOf
import id.co.ppa_github.core.domain.`object`.UserDetails
import id.co.ppa_github.core.domain.parameter.SearchParameter
import id.co.ppa_github.core.domain.parameter.UserDetailsParameter
import id.co.ppa_github.core.domain.response.SearchResponse
import kotlinx.coroutines.flow.Flow

interface IGithubRepository {

    suspend fun searchUsers(parameter: SearchParameter?): SearchResponse?

    suspend fun fetchUserDetails(parameter: UserDetailsParameter?): Flow<ResultOf<UserDetails?>>


}