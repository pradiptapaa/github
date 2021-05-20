package id.co.ppa_github.core.usecase

import id.co.ppa_github.core.data.IGithubRepository
import id.co.ppa_github.core.domain.parameter.UserDetailsParameter
import javax.inject.Inject

class GetUser
@Inject constructor(private val repository: IGithubRepository) {

    suspend operator fun invoke(parameter: UserDetailsParameter) =
        repository.fetchUserDetails(parameter)

}