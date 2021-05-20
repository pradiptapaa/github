package id.co.ppa_github.core.usecase

import id.co.ppa_github.core.data.IGithubRepository
import id.co.ppa_github.core.domain.parameter.SearchParameter
import javax.inject.Inject

class GetUsers
@Inject constructor(private val repository: IGithubRepository) {

    suspend operator fun invoke(parameter: SearchParameter) =
        repository.searchUsers(parameter)

}