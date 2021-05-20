package id.co.ppa_github.module.github.data

import id.co.base.ResultOf
import id.co.ppa_github.module.github.domain.`object`.UserDetails
import id.co.ppa_github.module.github.domain.parameter.SearchParameter
import id.co.ppa_github.module.github.domain.parameter.UserDetailsParameter
import id.co.ppa_github.module.github.domain.response.SearchResponse
import kotlinx.coroutines.flow.Flow

interface IGithubRepository {

    suspend fun searchUsers(parameter: SearchParameter?): SearchResponse?

    suspend fun fetchUserDetails(parameter: UserDetailsParameter?): Flow<ResultOf<UserDetails?>>


}