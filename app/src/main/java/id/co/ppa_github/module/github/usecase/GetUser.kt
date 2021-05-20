package id.co.ppa_github.module.github.usecase

import id.co.ppa_github.module.github.data.IGithubRepository
import id.co.ppa_github.module.github.domain.parameter.UserDetailsParameter
import javax.inject.Inject

class GetUser
@Inject constructor(private val repository: IGithubRepository) {

    suspend operator fun invoke(parameter: UserDetailsParameter) =
        repository.fetchUserDetails(parameter)

}